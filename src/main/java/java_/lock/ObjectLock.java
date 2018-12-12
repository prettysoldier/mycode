
package java_.lock;

/**
 * @author Shuaijun He
 */
public class ObjectLock {
    public void test1() {
        synchronized (this) {
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

    public synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock myt2 = new ObjectLock();
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
        final ObjectLock objectLock2 = new ObjectLock();
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
