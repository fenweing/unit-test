package com.tuanbaol.unittest;

public class SleepTest {
    private static Object object;

    public static void main(String[] args) {
        new SleepTest().newThread();
    }

    private void newThread() {
        new Thread(() -> {
            try {
                while (true) {
                    if (object == null) {
                        object = this;
                    } else {
                        System.out.println(object == this);
                    }
                    Thread.sleep(500L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s1"
        ).start();
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("s2:" + (object == this));
                    Thread.sleep(500L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s2"
        ).start();
    }
}
