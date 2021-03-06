package source_code_java.java_.util.collection.list;

import java.util.Vector;

/**
 * @author Shuaijun He
 */
public class VectorTest {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 10; i++) {
                VectorTest.vector.add(i);
            }

            Thread removeThread = new Thread(() -> {
                for (int i = 0; i < VectorTest.vector.size(); i++) {
                    VectorTest.vector.remove(i);
                }
            });

            Thread getThread = new Thread(() -> {
                for (int i = 0; i < VectorTest.vector.size(); i++) {
                    System.out.println(VectorTest.vector.get(i));
                    System.out.println("size:" + VectorTest.vector.size());
                }
            });

            removeThread.start();
            getThread.start();

            while (Thread.activeCount() > 4) {
                System.out
                    .print(",Thread.activeCount()" + Thread.activeCount());
            }

        }
    }
}
