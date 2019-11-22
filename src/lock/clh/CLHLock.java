package lock.clh;

import lock.ExclusiveLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author created by zzz at 2019/11/12 14:28
 */

public class CLHLock implements ExclusiveLock {
    private AtomicReference<Node> tail;
    private ThreadLocal<Node> me;
    private ThreadLocal<Node> prev;

    public CLHLock() {
        tail = new AtomicReference<>(new Node());
        me = ThreadLocal.withInitial(Node::new);
        prev = ThreadLocal.withInitial(() -> null);
    }


    @Override
    public void lock() {
        Node meNode = me.get();
        meNode.isLocked = true;
        Node oldTail = tail.getAndSet(me.get());
        prev.set(oldTail);
        while (oldTail.isLocked) {
            // spin
        }
    }

    @Override
    public void unlock() {
        Node meNode = me.get();
        meNode.isLocked = false;
        me.set(prev.get());
    }

    private class Node {
        private volatile boolean isLocked = false;
    }
}
