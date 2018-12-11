
package java.lock;

/**
 * @author Shuaijun He
 */
public class MyWait {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start:" + System.currentTimeMillis());
        Object lock = new Object();
        synchronized (lock) {
            lock.wait(3000);
        }
        System.out.println("end:" + System.currentTimeMillis());
    }
}
