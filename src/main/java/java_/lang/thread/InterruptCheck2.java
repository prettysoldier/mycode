package java_.lang.thread;

/**
 * 进一步演示线程中断过程：上一个示例是当前线程中断自己，我们演示一下当前线程中断其他线程
 * 子线程在执行循环中，被从外部中断了
 *
 * 解释：
 * 1、那么B为什么还会执行？
 * 因为实际上只是发送了中断信号，子线程并没有中断
 * 事实上，interrupt方法只是改变目标线程的中断状态（interrupt status），而那些会抛出InterruptedException异常的方法，如wait、sleep、join等，都是在方法内部不断地检查中断状态的值。
 * 2、为什么C还会执行？
 * 因为虽然sleep触发了中断（是通过抛异常的方式来实现中断的！同时会清空中断标识！），但是被捕获了，中断标识也被清除了，子线程继续执行
 *
 * Thread.interrupted方法
 * Thread类方法：该方法返回当前线程的内部中断状态，然后清除中断状态（置为false）
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class InterruptCheck2 {
    public static void main(String[] args) {
        Thread t= new Thread(()->{
            System.out.println("A: isInterrupted="+Thread.currentThread().isInterrupted());
            int i = 0;
            // 此过程中，被外部中断
            while(i > -1){
                i++;
            }
            System.out.println("B: isInterrupted="+Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(10);
                System.out.println("线程没有被中断");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程被中断了");
            }
            System.out.println("C: isInterrupted="+Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("E: isInterrupted="+Thread.currentThread().isInterrupted());
            System.out.println("F: Thread.interrupted()="+Thread.interrupted());
            System.out.println("G: isInterrupted="+Thread.currentThread().isInterrupted());
        });
        t.start();

        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        System.out.println("D: isInterrupted="+t.isInterrupted());
    }
}
