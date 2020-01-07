package java_.lang.thread;

/**
 * 如果线程中没有可以中断的语句，此线程还可以实际中断吗？
 * 答案是不会实际中断！！
 *
 * 事实上，interrupt方法只是改变目标线程的中断状态（interrupt status），而那些会抛出InterruptedException异常的方法，
 * 如wait、sleep、join等，都是在方法内部不断地检查中断状态的值。
 *
 * 哪些是可以中断的语句？
 * 可以抛出InterruptedException异常的方法。Thread.sleep() thread.join() object.wait()
 * AbstractQueuedSynchronizer.acquireInterruptibly()
 *   acquireSharedInterruptibly()
 *   acquireSharedInterruptibly()
 *   reportInterruptAfterWait()
 *   tryAcquireNanos()
 * ConditionObject.await()系列
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class InterruptCheck3 {
    public static void main(String[] args) {
        Thread t= new Thread(()->{
            System.out.println("A: isInterrupted="+Thread.currentThread().isInterrupted());
            int i = 0;
            // 此过程中，被外部中断
            while(i > -1){
                i++;
            }
            System.out.println("B: isInterrupted="+Thread.currentThread().isInterrupted());
        });
        t.start();

        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        System.out.println("C: isInterrupted="+t.isInterrupted());
    }
}
