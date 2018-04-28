/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.lock;

/**
 * 方法所属实例不是同一个对象；对象锁是同一个对象
 * 会有什么结果？
 * 结论：与所属对象无关，只与对象锁相关
 *
 * @author Shuaijun He
 */
public class ObjectLock2 {
    private static Object lock = new Object();

    public void test1() {
        synchronized (ObjectLock2.lock) {
            int i = 5;
            while (i-- > 0) {
                System.out
                    .println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public void test2() {
        synchronized (ObjectLock2.lock) {
            int i = 5;
            while (i-- > 0) {
                System.out
                    .println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock2 myt2 = new ObjectLock2();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test2();
            }
        }, "test2");
        // 如果对象不同，对象锁之间互不干扰
        final ObjectLock2 objectLock2 = new ObjectLock2();
        Thread test3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock2.test1();
            }
        }, "test3");
        test1.start();
        test2.start();
        test3.start();
//             TestRunnable tr=new TestRunnable();
//             Thread test3=new Thread(tr);
//             test3.start();
    }

}
