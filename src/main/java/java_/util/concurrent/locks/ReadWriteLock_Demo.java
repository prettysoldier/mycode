package java_.util.concurrent.locks;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 所有读锁释放之后，才能获取写锁
 *
 * @author Shuaijun He
 */
public class ReadWriteLock_Demo {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = this.lock.readLock();
    private Lock writeLock = this.lock.writeLock();

    public void processRead() {
        try {
            this.readLock.lock();
            System.out.println(Thread.currentThread() + "获取了读锁" + System.currentTimeMillis());
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // do something
        } finally {
            this.readLock.unlock();
        }
    }

    public void processWrite() {
        try {
            this.writeLock.lock();
            System.out.println(Thread.currentThread() + "获取了写锁" + System.currentTimeMillis());
            this.readLock.lock();
            System.out.println(Thread.currentThread() + "获取了读锁" + System.currentTimeMillis());
            // do something
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            this.writeLock.unlock();
        }

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            this.readLock.unlock();
        }

    }

    public static void main(String[] args) throws Exception {
        ReadWriteLock_Demo readWriteLock = new ReadWriteLock_Demo();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {readWriteLock.processRead();});
        Thread.sleep(1 * 1000);
        executorService.execute(() -> {readWriteLock.processRead();});

        executorService.execute(() -> {readWriteLock.processWrite();});
        executorService.execute(() -> {readWriteLock.processRead();});
        executorService.execute(() -> {readWriteLock.processWrite();});
        executorService.execute(() -> {readWriteLock.processRead();});

    }
}

class RWTreeMap {
    private final Map<String, String> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(String key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public String[] allKeys() {
        r.lock();
        try {
            return (String[]) m.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public String put(String key, String value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
            m.clear();
        } finally {
            w.unlock();
        }
    }
}