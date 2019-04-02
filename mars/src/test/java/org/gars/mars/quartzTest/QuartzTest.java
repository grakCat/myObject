package org.gars.mars.quartzTest;

import org.gars.mars.quartz.MyQuartz;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/11/9.
 *
 * @author grayCat
 * @since 1.0
 */
public class QuartzTest extends MyQuartz {

    @Test
    public void quartz() throws SchedulerException {
        QuartzTest quartz = new QuartzTest();
        JobDataMap jobMap = new JobDataMap();
        jobMap.put("name", "quartz");
        jobMap.put("time", 50);
        quartz.delayTimmer(5, TimeUnit.SECONDS, GrayJob.class, jobMap);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
