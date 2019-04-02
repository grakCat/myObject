package org.gars.mars.configtest.jsonConfig.test;

import org.gars.mars.configtest.jsonConfig.JsonConfigLoadBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/12/6.
 *
 * @author grayCat
 * @since 1.0
 */
@Component
@Order(1)
public class Club1000 extends JsonConfigLoadBean<Club1000> {

    public static Club1000 club = new Club1000();

    private int maxNum = 10;
    private int closeOpenWaitDay = 1;
    private int minRoomCard = 10;
    private int insteadRoomNumMax = 10;
    private int totalRoomNumMax = 10;
    private boolean test = true;

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getCloseOpenWaitDay() {
        return closeOpenWaitDay;
    }

    public void setCloseOpenWaitDay(int closeOpenWaitDay) {
        this.closeOpenWaitDay = closeOpenWaitDay;
    }

    public int getMinRoomCard() {
        return minRoomCard;
    }

    public void setMinRoomCard(int minRoomCard) {
        this.minRoomCard = minRoomCard;
    }

    public int getInsteadRoomNumMax() {
        return insteadRoomNumMax;
    }

    public void setInsteadRoomNumMax(int insteadRoomNumMax) {
        this.insteadRoomNumMax = insteadRoomNumMax;
    }

    public int getTotalRoomNumMax() {
        return totalRoomNumMax;
    }

    public void setTotalRoomNumMax(int totalRoomNumMax) {
        this.totalRoomNumMax = totalRoomNumMax;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    protected Class<Club1000> getTClass() {
        return Club1000.class;
    }

    @Override
    protected void getJsonConfig(Club1000 club) {
        Club1000.club = club;
    }
}
