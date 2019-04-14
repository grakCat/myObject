package com.stude.helloworld;

/**
 * Created on 2019/4/12.
 *
 * @author Grak
 * @since 1.0
 */
public class Adapter {

    public static void main(String[] args) {
        Car baoMa = new BaoMa();
        baoMa.run();

        Car tuoLaJi = new TuoLaJI();
        tuoLaJi.run();
    }

    public static class TuoLaJI implements Car{

        @Override
        public void run() {
            System.out.println("速度7码");
        }
    }

    public static class BaoMa implements Car{

        @Override
        public void run() {
            System.out.println("速度700码");
        }
    }

    public interface Car{
        void run();
    }
}
