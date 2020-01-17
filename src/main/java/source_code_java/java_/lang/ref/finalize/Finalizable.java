package source_code_java.java_.lang.ref.finalize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hsj
 * @create 2019/12/31
 */
public class Finalizable {

    static AtomicInteger aliveCount = new AtomicInteger(0);

    Finalizable() {
        aliveCount.incrementAndGet();
    }

    @Override
    protected void finalize() throws Throwable {
        Finalizable.aliveCount.decrementAndGet();
    }

    public static void main(String args[]) {
        for (int i = 0;; i++) {
            Finalizable f = new Finalizable();
            if ((i % 100_000) == 0) {
                System.out.format("After creating %d objects, %d are still alive.%n", new Object[] {i, Finalizable.aliveCount.get() });
            }
        }
    }
}
