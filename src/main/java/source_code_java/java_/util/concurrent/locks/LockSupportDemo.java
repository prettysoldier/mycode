package source_code_java.java_.util.concurrent.locks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 与 wait notify 的区别：
 * 1. park方法是会响应中断的，但是不会抛出异常。(也就是说如果当前调用线程被中断，则会立即返回但不会抛出中断异常)
 * 2. LockSupport unpark park 谁先谁后都可以；wait notify 必须先wait 再 notify，否则不能唤醒。
 * @author shuaijunhe
 * @create 2019/10/14
 * @description
 */
public class LockSupportDemo {
    public static void main(String[] args) throws Exception{
        FifoMutex mutex = new FifoMutex();
        MyThread a1 = new MyThread("a1", mutex);
        MyThread a2 = new MyThread("a2", mutex);
        MyThread a3 = new MyThread("a3", mutex);

        a1.start();
        a2.start();
        a3.start();

        a1.join();
        a2.join();
        a3.join();

        assert MyThread.count == 300;
        System.out.print("Finished");
    }

    static class MyThread extends Thread {
        private String name;
        private FifoMutex mutex;
        public static int count;

        public MyThread(String name, FifoMutex mutex) {
            this.name = name;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                mutex.lock();
                count++;
                System.out.println("name:" + name + "  count:" + count);
                mutex.unlock();
            }
        }
    }

    static class FifoMutex {

        //    private final AtomicBoolean locked = new AtomicBoolean(false);
        private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

        public void lock(){
            Thread current = Thread.currentThread();
            waiters.add(current);

            /**
             * https://segmentfault.com/a/1190000015562456
             *
             * LockSupport类的核心方法其实就两个：park()和unark()，其中park()方法用来阻塞当前调用线程，unpark()方法用于唤醒指定线程。
             * 这其实和Object类的wait()和signial()方法有些类似，但是LockSupport的这两种方法从语意上讲比Object类的方法更清晰，
             * 而且可以针对指定线程进行阻塞和唤醒。
             *
             * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，可以把许可看成是一种(0,1)信号量（Semaphore），
             * 但与 Semaphore 不同的是，许可的累加上限是1。
             *
             * 初始时，permit为0，当调用unpark()方法时，线程的permit加1，当调用park()方法时，如果permit为0，则调用线程进入阻塞状态。
             *
             * 要注意如下三个问题：
             * 1.park方法的调用一般要放在一个循环判断体里面。之所以这样做，是为了防止线程被唤醒后，不进行判断而意外继续向下执行，
             * 这其实是一种Guarded Suspension 的多线程设计模式。
             *
             * 2.park方法是会响应中断的，但是不会抛出异常。(也就是说如果当前调用线程被中断，则会立即返回但不会抛出中断异常)
             *
             * 3.park的重载方法park(Object blocker)，会传入一个blocker对象，所谓Blocker对象，其实就是当前线程调用时所在调用对象
             * （如上述示例中的FIFOMutex对象）。该对象一般供监视、诊断工具确定线程受阻塞的原因时使用。
             */
            while(waiters.peek() != current ){
                LockSupport.park();
            }

            waiters.remove();
        }

        public void unlock(){
//        locked.set(false);
            LockSupport.unpark(waiters.peek());
        }
    }
}



