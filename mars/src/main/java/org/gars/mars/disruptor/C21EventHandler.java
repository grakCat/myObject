package org.gars.mars.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created on 2019/1/11.
 *
 * @author grayCat
 * @since 1.0
 */
public class C21EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 20;
        System.out.println(System.currentTimeMillis() + ": c2-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 20;
        System.out.println(System.currentTimeMillis() + ": c2-1 consumer finished.number=" + number);
    }
}
