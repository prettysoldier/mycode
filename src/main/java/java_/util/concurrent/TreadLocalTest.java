package java_.util.concurrent;

/**
 * @author Shuaijun He
 */
import java.util.HashMap;

public class TreadLocalTest {

    static ThreadLocal<HashMap> map0 = new ThreadLocal<HashMap>() {
        @Override
        protected HashMap initialValue() {
            System.out.println(Thread.currentThread().getName()
                + "initialValue");
            return new HashMap();
        }
    };

    public void run() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new T1(i));
        }
        for (Thread run : runs) {
            run.start();
        }
    }

    public static class T1 implements Runnable {
        int id;

        public T1(int id0) {
            this.id = id0;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            HashMap map = TreadLocalTest.map0.get();
            for (int i = 0; i < 10; i++) {
                map.put(i, i + this.id * 100);
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ':' + map);
        }
    }

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {
        TreadLocalTest test = new TreadLocalTest();
        test.run();
    }

}