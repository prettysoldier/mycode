/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Shuaijun He
 */
public class Client {

    static final int SIZE = 100_0000;

    static boolean log = false;

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

        for (int round = Client.SIZE/10 ; round <= Client.SIZE; round += Client.SIZE / 10) {
            System.out.println("round:" + round);
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

//            new MyInsertionSort().sortWithLog(insertSort);
            new MyShellSort().sortWithLog(shellArr);
            new MyQuickSort().sortWithLog(quickList);
            new MyBinaryHeapSort().sortWithLog(heapArr);
            new MyMergeSort().sortWithLog(mergeArr);
            new MyQuickSort().sortWithLog(quickArr);

            System.out.println("-----------------------------");
        }
    }
}
