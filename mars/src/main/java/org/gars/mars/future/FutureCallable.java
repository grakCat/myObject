package org.gars.mars.future;

import java.util.concurrent.Callable;

/**
 * Created on 2018/11/9.
 *
 * @author grayCat
 * @since 1.0
 */
public class FutureCallable implements Callable {

    private FutureInfo info;

    public FutureCallable(FutureInfo info) {
        this.info = info;
    }

    @Override
    public FutureInfo call() throws Exception {
        Thread.sleep(5000);
        info.mm += 10;
        return this.info;
    }
}
