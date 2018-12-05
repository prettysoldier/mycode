package test.algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Shuaijun He
 */
public class Client {

    /**
     * int最大[-21.47亿,+21.47]
     */
    static final int SIZE = 10_0000;

    static boolean log = false;

    private static boolean isBucketSort = true;

    static {
//        Client.log = true;
    }

    public static void main(String[] args) {

        Random r = new Random();
        List<Integer> ranArr = new ArrayList<>(Client.SIZE);
        for (int i = 0; i < Client.SIZE; i++) {
            ranArr.add(r.nextInt(Client.SIZE));
        }
        if (Client.log) {
            for (Integer i : ranArr) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
        int round = Client.isBucketSort ? Client.SIZE : Client.SIZE / 10;
        for (; round <= Client.SIZE; round += Client.SIZE / 10) {
            System.out.println("round:" + round + " go~~");
            List<Integer> quickList = new ArrayList<>();
            for (int i = 0; i < round; i++) {
                quickList.add(ranArr.get(i));
            }

            Integer[] mergeArr = new Integer[quickList.size()];
            quickList.toArray(mergeArr);

            Integer[] insertSort = new Integer[quickList.size()];
            quickList.toArray(insertSort);

            Integer[] shellArr = new Integer[quickList.size()];
            quickList.toArray(shellArr);

            Integer[] heapArr = new Integer[quickList.size()];
            quickList.toArray(heapArr);

            Integer[] quickArr = new Integer[quickList.size()];
            quickList.toArray(quickArr);

            Integer[] bucketArr = new Integer[quickList.size()];
            quickList.toArray(bucketArr);
            // 此算法MyInsertionSort对于大合集简直是灾难！
//            new MyInsertionSort().sortWithLog(insertSort);
//            new MyShellSort().sortWithLog(shellArr);
//            new MyQuickSort().sortWithLog(quickList);
//            new MyBinaryHeapSort().sortWithLog(heapArr);
//            new MyMergeSort().sortWithLog(mergeArr);
//            new MyQuickSort().sortWithLog(quickArr);
//            new MyBucketSort().sortWithLog4Integer(bucketArr);

            System.out.println("===============");
        }
    }
}
