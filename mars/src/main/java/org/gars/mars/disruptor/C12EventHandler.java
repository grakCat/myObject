package org.gars.mars.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class C12EventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number *= 10;
        System.out.println(System.currentTimeMillis() + ": c1-2 consumer finished.number=" + number);
    }

}
