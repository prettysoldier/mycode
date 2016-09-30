/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Shuaijun He
 */
public class MyReadWriteLock {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = this.lock.readLock();
    private Lock writeLock = this.lock.writeLock();

    public void processRead() {
        try {
            this.readLock.lock();
            // do something
        } finally {
            this.readLock.unlock();
        }
    }

    public void processWrite() {
        try {
            this.writeLock.lock();
            // do something
        } finally {
            this.writeLock.unlock();
        }
    }
}
