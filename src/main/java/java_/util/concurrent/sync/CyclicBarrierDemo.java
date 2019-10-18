package java_.util.concurrent.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：可以循环使用的栅栏，通过ReentrantLock和Condition实现！！
 *
 * 注意：使用CyclicBarrier时，对异常的处理一定要小心，比如线程在到达栅栏前就抛出异常，
 * 此时如果没有重试机制，其它已经到达栅栏的线程会一直等待（因为没有还没有满足总数），最终导致程序无法继续向下执行。
 * 所以必须处理好异常！
 *
 * 问题：if(Thread.interrupted()){} 当前线程是否被中断，中断后执行操作。线程被中断后还能继续执行吗？（CyclicBarrier的209行）
 * 实际上只是发送了中断消息，还没有执行中断。线程不会中断！
 *
 *
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException{
        /**
         * 多个运动员线程，在到达指定数目的等待线程后，一起继续运行。
         * 第三个运动员最先到达，其他运动员还未到达，此时中断第三个运动员线程，那么后续的所有运动员都会抛出栅栏损坏异常！
         */
        // 运动员数
        int N = 5;
        CyclicBarrier cb = new CyclicBarrier(N, () -> {
            System.out.println("****** 所有运动员已准备完毕，发令枪：跑！******");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        List<Thread> list = new ArrayList<>(N);
        for (int i = 1; i <= N; i++) {
            Thread t = new Thread(new PrepareWork(cb), "运动员[" + i + "]");
            t.setName(i + "");
            t.start();
            list.add(t);
        }
        Thread.sleep(3000);
        // 运动员[3]置中断标志位
        list.get(2).interrupt();
        Thread.sleep(4000);
        System.out.println("Barrier是否损坏：" + cb.isBroken());
    }


    private static class PrepareWork implements Runnable {
        private CyclicBarrier cb;

        PrepareWork(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {

            try {
                System.out.println(Thread.currentThread().getName() + ": 准备");
                /**
                 * 注意：使用CyclicBarrier时，对异常的处理一定要小心，比如线程在到达栅栏前就中断了，
                 * 此时如果没有重试机制，其它已经到达栅栏的线程会一直等待（因为没有还没有满足总数），最终导致程序无法继续向下执行。
                 */
                if(Thread.currentThread().getName().equals("3")){

                    Thread.sleep(2000);
                }else{
                    Thread.sleep(4000);
                }
                cb.await();          // 在栅栏等待
                System.out.println(Thread.currentThread().getName() + ": 跑...");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": 被中断");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + ": 抛出BrokenBarrierException");
            }
        }
    }
}
