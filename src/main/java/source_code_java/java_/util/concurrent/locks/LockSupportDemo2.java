package source_code_java.java_.util.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * 原理：
 *      unpark函数为线程提供“许可(permit)”，线程调用park函数则等待“许可”。
 *      这个有点像信号量，但是这个“许可”是不能叠加的，“许可”是一次性的。
 *      unpark函数可以先于park调用
 *
 * LockSupport实际上是调用了Unsafe类里的函数，归结到Unsafe里，只有两个函数：
 * 1     public native void unpark(Thread jthread);
 * 2     public native void park(boolean isAbsolute, long time); *
 * isAbsolute参数是指明时间是绝对的，还是相对的。
 *
 * 与Object锁的重大区别：
 *      unpark(thread1) 先于 thread1.park()。thread1.park() 会立即返回，不会阻塞！！
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class LockSupportDemo2 {

    public static void main(String[] args) throws Exception {

//        notInterrupt();

        unparkBeforePark();
    }

    /**
     * 此例子说明 LockSupport 与 中断 不是一回事！！
     * @throws InterruptedException
     */
    private static void notInterrupt() throws InterruptedException {
        Thread t= new Thread(()->{
            System.out.println("A: isInterrupted="+Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("B: isInterrupted="+Thread.currentThread().isInterrupted());
        });
        t.start();

        Thread.sleep(2000);
        // 此处是false
        System.out.println("C: isInterrupted="+t.isInterrupted());
        // C: isAlive=true
        System.out.println("C: isAlive="+t.isAlive());

        LockSupport.unpark(t);
        System.out.println("D: isInterrupted=" + t.isInterrupted());
    }

    /**
     * unpark(thread1) 先于 thread1.park()。thread1.park() 会立即返回，不会阻塞！！
     */
    private static void unparkBeforePark(){
        System.out.println("-------------");
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.nanoTime();
            LockSupport.park();
            System.out.println("park 了多少：" + (System.nanoTime() - start));
        });
        t2.start();


        Thread t1 = new Thread(()->{
            LockSupport.unpark(t2);
            System.out.println("t2 unpark");
        });
        t1.start();


    }
}
