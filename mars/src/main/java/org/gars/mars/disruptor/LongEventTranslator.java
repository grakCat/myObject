package org.gars.mars.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * Created on 2019/1/11.
 * LongEvent runBuffer中存储的对象
 * SendMessage 不同生成者发过来的消息
 * 这里就是把SendMessage转化成LongEvent
 * @since 1.0
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, SendMessage> {
    @Override
    public void translateTo(LongEvent event, long sequence, SendMessage arg0) {
        event.setNumber((long) arg0.getSid());
    }
}
