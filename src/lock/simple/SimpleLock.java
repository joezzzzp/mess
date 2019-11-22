package lock.simple;

import lock.ExclusiveLock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author created by zzz at 2019/11/13 11:42
 */

public class SimpleLock implements ExclusiveLock {

    private static final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(0);
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
//            if (hasQueuedPredecessors()) {
//                return false;
//            }
            int state = getState();
            if (state == 1) {
                return false;
            }
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();
            if (state == 1) {
                return compareAndSetState(1, 0);
            }
            return false;
        }

    }
}
