package algorithm.selection.problem;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Shuaijun He
 */
public class Client {

    static final int SIZE = 100;

    static boolean log = false;

    static {
        Client.log = true;
    }

    public static void main(String[] args) {

        Random r = new Random();
        Integer[] a = new Integer[Client.SIZE];
        for (int i = 0; i < Client.SIZE; i++) {
            a[i] = r.nextInt(Client.SIZE);
        }
        if (Client.log) {
            for (Integer i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

        for (int round = Client.SIZE / 10; round <= Client.SIZE; round += Client.SIZE / 10) {
            System.out.println("round:" + round);
            Integer[] heapArr = Arrays.copyOf(a, round);
            Integer[] quickArr = Arrays.copyOf(a, round);

            new SelectByHeap().selectWithLog(heapArr, round / 2);
            new QuickSelect().selectWithLog(quickArr, round / 2);
            System.out.println("-----------------------------");
        }
    }
}
