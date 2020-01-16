package java_.syntax.keyword.synchronized_;

/**
 * synchronzed锁不可以中断？
 *
 * @author heshuaijun
 * @date 2020/1/14 23:31
 */
public class SynchronizedCannotInterrupt {

    private static Object lock = new Object();
    private static void f1(){
        synchronized (lock){


            System.out.println(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void f2(){
        synchronized (lock){

            while(true){
                System.out.println(2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main (String[] args) throws Exception{

        Thread t1 = new Thread(()->f1());
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(()->f2());
        t2.start();

        Thread.sleep(1000);

        t1.interrupt();
    }

}
