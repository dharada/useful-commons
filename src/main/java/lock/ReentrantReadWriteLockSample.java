package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockSample {

    public static void lock() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

        reentrantReadWriteLock.writeLock().lock();

        reentrantReadWriteLock.readLock().lock();


    }
}
