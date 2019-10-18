package java_.util.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * 演示park和unpark
 * 此例子说明 LockSupport 与 中断 不是一回事！！
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class LockSupportDemo2 {

    public static void main(String[] args) throws Exception {
        Thread t= new Thread(()->{
            System.out.println("A: isInterrupted="+Thread.currentThread().isInterrupted());
            int i = 0;
            // 此过程中，被外部中断
            while(i > -1){
                i++;
            }
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

    }
}
