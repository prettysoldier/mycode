package java_.util.concurrent.locks.read_write_lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Shuaijun He
 */
public class ReadWriteLock2 {

    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void test() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        final Lock readLock = lock.readLock();
        final Lock writeLock = lock.writeLock();
        final ReadWriteLock2 resource = new ReadWriteLock2();
        final Random random = new Random();

        for (int i = 0; i < 20; ++i) {//读线程
            new Thread() {
                @Override
                public void run() {
                    while (true) {

                        readLock.lock();
                        try {
                            System.out.println(new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
                                + " - "
                                + Thread.currentThread()
                                + "获取了读锁，读取的数据为：" + resource.getValue());
                            Thread.sleep(random.nextInt(1000));//随机休眠
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            readLock.unlock();
                        }
                    }
                }
            }.start();
        }
        for (int i = 0; i < 10; ++i) {//写线程
            new Thread() {
                @Override
                public void run() {
                    while (true) {

                        writeLock.lock();
                        try {
                            resource.setValue(resource.getValue() + 1);
                            System.out.println(new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
                                + " - "
                                + Thread.currentThread()
                                + "获取了写锁，修正数据为：" + resource.getValue());
                            Thread.sleep(random.nextInt(1000));//随机休眠
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            writeLock.unlock();
                        }
                        try {
                            Thread.sleep(random.nextInt(10000));
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }//随机休眠
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        new ReadWriteLock2().test();
    }

}
