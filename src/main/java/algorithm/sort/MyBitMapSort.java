package algorithm.sort;

import sun.jvm.hotspot.utilities.BitMap;

/**
 * TODO
 * @author heshuaijun
 * @date 2019/12/29 13:00
 */
public class MyBitMapSort {
    /**
     *
     * @param a
     * @param max a中数字的最大值
     */
    public static int[] bitSort(int[] a, int max) {
        BitMap map = new BitMap(max);
        for (int i = 0; i < map.size(); i++) {
            map.atPut(i, true);
        }
        return a;
    }
}
