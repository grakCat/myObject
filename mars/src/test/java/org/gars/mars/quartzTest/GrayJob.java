package org.gars.mars.quartzTest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018/11/9.
 *
 * @author grayCat
 * @since 1.0
 */
public class GrayJob implements Job {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String name;

    private int time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Ok:" + getName() + getTime());
    }
}
