package org.gars.mars.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class Main {

    /**
     * 优点
     * 1.数组长度2^n，通过位运算，加快定位的速度。（定位位置快）
     * 2.因为它是数组，所以要比链表快（读取，修改快）
     * 3.数组中的元素是会被预加载的，不需要花大量的时间用于垃圾回收（不需要重复实例化，垃圾回收。不会浪费重复时间）
     * 4.使用CAS的乐观并发控制来保证指针自增的原子性（比加锁快）
     *
     *  RingBuffer上只有一个指针，表示当前RingBuffer上消息写到了哪里
     *  每个消费者会维护一个sequence表示自己在RingBuffer上读到哪里（RingBuffer上实际有消费者数+1个指针）
     *
     *  RingBuffer
     *  环形的缓冲区，核心类，是线程间交换数据的中转地。
     *  SequenceDisruptor
     *  顺序递增的序号来编号管理通过其进行交换的数据（事件）。（消息写到了哪里）
     *  Sequence Barrier
     *  Consumer的 Sequence 的引用，决定 Consumer 是否还有可处理的事件的逻辑
     *  Wait Strategy
     *  定义 Consumer 如何进行等待下一个事件的策略
     *  Event
     *  交换的数据被称为事件(Event)
     *  EventProcessor
     *  监听RingBuffer的事件，并消费可用事件
     *  EventHandler
     *  Consumer 的真正实现，消费逻辑
     *  Producer
     *  事件处理器，监听RingBuffer的事件，并消费可用事件
     *
     *  执行过程：
     *  Producer(生产者)发布一个任务
     *  Sequence在RingBuffer中找到一个空的位置
     *  Sequencer绑定当前任务存放位置
     *  EventProcessor找到RingBuffer中准备好的任务
     *  并发布给EventHandler(消费者)
     * @param args
     */
//    public static void main(String[] args) {
//        //1.创建RingBuffer存储对象的工厂方法
//        EventFactory<LongEvent> eventFactory = new LongEventFactory();
//        //2.环形队列长度，必须是2的N次方
//        int bufferSize = 1024 * 1024;
//        //3.创建一个可缓存的线程，提供线程来触发Consumer 的事件处理
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        //4.ProducerType.SINGLE单生产者模式，MULTI多生产者模式
//        /**
//         * 可选的等待策略
//         * BlockingWaitStrategy是最慢的等待策略，但也是CPU使用率最低和最稳定的选项。(中等)
//         * SleepingWaitStrategy最好用在不需要低延迟，而且事件发布对于生产者的影响比较小的情况下。比如异步日志功能。（低cpu高延迟）
//         * YieldingWaitStrategy在减低系统延迟的同时也会增加CPU运算量,例如：在开启超线程的时候。（高cpu低延迟）
//         * BusySpinWaitStrategy是性能最高的等待策略，同时也是对部署环境要求最高的策略。例如：在禁用超线程技术的时候。（高cpu低延迟）
//         */
//
//        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize,threadFactory, ProducerType.SINGLE, new BlockingWaitStrategy());
//        //这里是调用各种不同方法的地方.
//        serialWithPool(disruptor);
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        /**
//         * long sequence = ringBuffer.next();事件队列 下一个槽
//         * LongEvent longEvent = ringBuffer.get(sequence); 取出空的事件队列
//         * longEvent.setValue(data);获取事件队列传递的数据
//         * ringBuffer.publish(sequence);
//         */
//        ringBuffer.publishEvent(new LongEventTranslator(), new SendMessage(10));
//        ringBuffer.publishEvent(new LongEventTranslator(), new SendMessage(100));
//    }

    public static void main(String[] args) throws InterruptedException {
        //创建一个ringBuffer
        RingBuffer<LongEvent> ringBuffer = RingBuffer.createSingleProducer(new LongEventFactory(), 1024 * 1024);
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        WorkHandler<LongEvent> handler = new C11EventHandler();
        WorkerPool<LongEvent> workerPool = new WorkerPool<LongEvent>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), handler);
        workerPool.start(executor);

        //下面这个生产8个数据
        for(int i=0;i<8;i++){
            long seq=ringBuffer.next();
            ringBuffer.get(seq).setNumber((long)Math.random()*9999);
            ringBuffer.publish(seq);
        }
        Thread.sleep(1000);
        workerPool.halt();
        executor.shutdown();
    }

    /**
     * 并行计算实现,c1,c2互相不依赖
     * <br/>
     * p --> c11
     * --> c21
     */
    public static void parallel(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C21EventHandler());
        disruptor.start();
    }

    /**
     * 串行依次执行
     * <br/>
     * p --> c11 --> c21
     *
     * @param disruptor
     */
    public static void serial(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }

    /**
     * 菱形方式执行
     * 同时执行C11和c12，然后执行c21。他们收到的数据都是一样的，包括
     * @param disruptor
     */
    public static void diamond(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C12EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }

    /**
     * 并行计算实现,c1,c2互相不依赖,同时C1，C2分别有2个实例
     * <br/>
     * p --> c11
     * --> c21
     */
    public static void parallelWithPool(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWithWorkerPool(new C11EventHandler(), new C11EventHandler());
        disruptor.handleEventsWithWorkerPool(new C21EventHandler(), new C21EventHandler());
        disruptor.start();
    }

    /**
     * 串行依次执行,同时C11，C21分别有2个实例
     * <br/>
     * p --> c11 --> c21
     *
     * @param disruptor
     */
    public static void serialWithPool(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWithWorkerPool(new C11EventHandler(), new C11EventHandler()).then(new C21EventHandler(), new C21EventHandler());
        disruptor.start();
    }
}
