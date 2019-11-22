package lock;

import lock.clh.CLHLock;
import lock.simple.SimpleLock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author created by zzz at 2019/11/12 15:54
 */

public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        ExclusiveLock lock = new SimpleLock();
        final List<String> list = Collections.synchronizedList(new LinkedList<>());
        final List<String> record = Collections.synchronizedList(new LinkedList<>());
        for (int i = 0; i < 10; i++) {
            new TestThread(i, lock, list, record).start();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        String listStr = String.join("", list);
        String recordStr = String.join("", record);
        System.out.println(listStr);
        System.out.println(recordStr);
        System.out.println(listStr.equals(recordStr));
    }

    private static class TestThread extends Thread {

        private int number;

        private ExclusiveLock lock;

        private List<String> record;

        private List<String> list;

        public TestThread(int number, ExclusiveLock lock, List<String> list, List<String> record) {
            this.number = number;
            this.lock = lock;
            this.record = record;
            this.list = list;
        }

        @Override
        public void run() {
            record.add(String.valueOf(number));
            lock.lock();
            try {
               list.add(String.valueOf(number));
            } finally {
                lock.unlock();
            }
        }
    }
}
