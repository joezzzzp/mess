package lock;

/**
 * @author created by zzz at 2019/11/13 14:08
 */

public interface ExclusiveLock {

    void lock();

    void unlock();
}
