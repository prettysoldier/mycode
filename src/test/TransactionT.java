
package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shuaijun He
 */
public class TransactionT {
    private final static ExecutorService cachedThreadPool = Executors
        .newCachedThreadPool();

    public void test() {
        TransactionT.cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {

                TransactionT.this.dosmt();
                System.out.println(TransactionT.this);
            }
        });
        System.out.println("hshs");
    }

    public void dosmt() throws RuntimeException {
        System.out.println("do some");
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        TransactionT t = new TransactionT();
        System.out.println(t);
        t.test();
    }
}
