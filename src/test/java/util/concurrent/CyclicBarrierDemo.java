package test.java.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Desc 所有线程等待某一下状态，等都达到了，再继续执行
 * 如果parties < await()的线程数，则只有parties个线程继续执行，剩余的线程将一直等待
 * 如果parties > await()的线程数，则所有线程将一直等待，因为表示还有个线程还没有达到状态
 * 所以建议parties 与 await() 相等
 * @Author shuaijunhe
 * @CreateTime 2018/12/6 13:59
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(5);
        for(int i=0;i<N;i++){
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
                Thread.sleep((long)(Math.random() * 5000));      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");

        }
    }
}
