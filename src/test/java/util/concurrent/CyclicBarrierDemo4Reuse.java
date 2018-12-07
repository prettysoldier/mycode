package test.java.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Desc 从执行结果可以看出，在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。
 * 而CountDownLatch无法进行重复使用。
 * 注意保证第一次任务都执行过cyclicBarrier.await()之后，才能进行再次使用，不然结果不可预知
 * 如果不想等待第一次任务，可以调用reset()方法重置，如果这样做的话，第一次任务会报BrokenBarrierException异常
 * @Author shuaijunhe
 * @CreateTime 2018/12/6 13:59
 **/
public class CyclicBarrierDemo4Reuse {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(2000);
//            barrier.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                //以睡眠来模拟写入数据操作
                Thread.sleep((long)(Math.random() * 6000));
                System.out.println("线程" + Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕");
        }
    }
}
