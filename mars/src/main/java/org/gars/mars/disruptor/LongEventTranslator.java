package org.gars.mars.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, SendMessage> {
    @Override
    public void translateTo(LongEvent event, long sequence, SendMessage arg0) {
        event.setNumber((long) arg0.getSid());
    }
}
