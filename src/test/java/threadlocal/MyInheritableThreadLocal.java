/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java.threadlocal;

import java.util.Date;

/**
 * @author Shuaijun He
 */
public class MyInheritableThreadLocal extends InheritableThreadLocal<Object> {

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    public static void main(String[] args) {
        ThreadA th = new ThreadA();
        th.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(" main线程 :"
                + Tools.myInheritableThreadLocal.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        ThreadA th2 = new ThreadA();
//        th2.start();

    }
}

class Tools {
    public static MyInheritableThreadLocal myInheritableThreadLocal = new MyInheritableThreadLocal();
//    public static ThreadLocal<Object> myInheritableThreadLocal = new ThreadLocal<>();

}

class ThreadA extends Thread {
    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("threadA :"
                + Tools.myInheritableThreadLocal.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
