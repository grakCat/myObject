package org.gars.mars.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
