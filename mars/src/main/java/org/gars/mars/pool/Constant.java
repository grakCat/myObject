package org.gars.mars.pool;

/**
 * Created on 2018/11/30.
 *
 * @author grayCat
 * @since 1.0
 */
public class Constant {

    /**
     * 特殊牌
     */
    enum ExecutorType {
        COMMON(1, "通用线程池"),
        SAMPLE(2, "简单线程池");

        private int value;
        private String describe;

        ExecutorType(int value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        public int getValue() {
            return value;
        }

        public String getDescribe() {
            return describe;
        }
    }
}
