package com.stude.javabase.lamada;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2019/4/2.
 *
 * @author Grak
 * @since 1.0
 */
public class Lamada01 {

    public static void main(String[] args) {
        Lamada01 lamada = new Lamada01();
        lamada.lamadaBase01();
        lamada.lamadaBase02();
    }

    public void lamadaBase01(){
        //输入一个参数，返回一个参数
        Function<Integer,String> fu = (t) -> String.valueOf(t + 1);
        System.out.println(fu.apply(60));
        //输入2个参数，返回一个参数
        BiFunction<Integer,String,String> biFu = (a,b)-> a + b;
        System.out.println(biFu.apply(60,"传入二个参数"));

        //不传参数，获取一个返回值
        Supplier<String> su = () -> new Random().nextInt(10) + "";
        System.out.println("获取一个结果：" + su.get());

        //无返回值
        Consumer<String> co = System.out :: println;
        co.accept("直接调用一个方法");
    }

    public void lamadaBase02(){
        //generate 会生成无线个2的集合，配合limit获取10个2的集合
        List<Integer> list = Stream.generate(() -> 2).limit(10).collect(Collectors.toList());
        Integer[] arr = new Integer[10];
        list.toArray(arr);
        System.out.println("很多二：" + arr.toString());

        //iterate 第一个参数起始值，后面是每次修改值。skip获取位置5以后的参数，总共获取10个
        List<Integer> listIterator = Stream.iterate(2,x -> x + 1).skip(5).limit(10).collect(Collectors.toList());

        //list获取stream方式
        Stream streamlist = list.stream();
        //array获取stream方式
        Stream streamArr = Stream.of(arr);

        //拦截器filter
        list.stream().filter(t -> t % 2 == 0).forEach(System.out::println);

        //排序后获取第一个
        list.stream().sorted((a ,b)-> a- b).findFirst();
        //去重
        list.stream().distinct().forEach(System.out::println);
        //生成新的stream
        list.stream().map(x -> x * 5).forEach(System.out::println);
        //中途消费一次（peek），后面逻辑继续执行
        list.stream().peek(System.out::println).forEach(System.out::println);

        //===================统计参数获取===========================
        //1、最大值，最小值
        int max = list.stream().max(Comparator.comparing(t -> t)).get();
        int min = list.stream().min(Comparator.comparing(t -> t)).get();
        System.out.println("max:" + max + ", min:" + min);

        //2、方法二
        IntSummaryStatistics summary = list.stream().mapToInt(x -> x).summaryStatistics();
        summary.getMax();//最大值
        summary.getMin();//最小
        summary.getAverage();//平均
        summary.getCount();//总数
        summary.getSum();//总和

        //3、方法三  collect(Collectors方法可以获取不同的返回值，list、set、map等
        list.stream().collect(Collectors.maxBy((a,b) -> a - b)).get();
        list.stream().collect(Collectors.minBy((a,b) -> a - b)).get();
        list.stream().collect(Collectors.averagingDouble(a -> a));


    }
}
