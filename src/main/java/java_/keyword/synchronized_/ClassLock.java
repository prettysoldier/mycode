
package java_.keyword.synchronized_;

/**
 * @author Shuaijun He
 */
public class ClassLock {
    public void test1() {
        synchronized (ClassLock.class) {
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

    public static synchronized void test2() {
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
        final ClassLock myt2 = new ClassLock();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ClassLock.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
//        TestRunnable tr = new TestRunnable();
//        Thread test3 = new Thread(tr);
//        test3.start();
    }

}