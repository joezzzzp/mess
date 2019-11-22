package jvm.gc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author created by zzz at 2019/11/20 10:05
 */

public class GCTest {

    public static void main(String[] args) throws InterruptedException {
        Map<String, User> cache = new HashMap<>();
        int count = 0;
        while (count < 2000 * 1024) {
            cache.put(String.valueOf(count), new User());
            count++;
        }
        while (true) {
            new Child().start();
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private static class User {
        private byte[] b1 = new byte[100];
        private byte[] b2 = new byte[100];
        private byte[] b3 = new byte[100];
        private byte[] b4 = new byte[100];
        private byte[] b5 = new byte[100];
        private byte[] b6 = new byte[100];
        private byte[] b7 = new byte[100];
        private byte[] b8 = new byte[100];
        private byte[] b9 = new byte[100];
        private byte[] b10 = new byte[100];
        private byte[] b11 = new byte[100];
        private byte[] b21 = new byte[100];
        private byte[] b31 = new byte[100];
        private byte[] b41 = new byte[100];
        private byte[] b51 = new byte[100];
        private byte[] b61 = new byte[100];
        private byte[] b71 = new byte[100];
        private byte[] b81 = new byte[100];
        private byte[] b91 = new byte[100];
        private byte[] b101 = new byte[100];
    }

    private static class Child extends Thread {

        private Map<String, User> cache = new HashMap<>();

        @Override
        public void run() {
            int count = 0;
            while (count < 10) {
                for (int i = 0; i < 150 * 1024; i++) {
                    cache.put(String.valueOf(i), new User());
                }
                System.out.println("[thread]" + count);
                cache.clear();
                count++;
            }
        }
    }
}
