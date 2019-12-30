package algorithm.sort;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 自实现的位图
 * @author heshuaijun
 * @date 2019/12/30 22:21
 */
public class MyBitMap {
    // 0x1f
    private static final int mask = 31;
//    private int bitsPerWord = 32;
    // 2 ^ 5 = 32, 右移5位，相当于除以32
    private int shift = 5;
    private int[] data;

    private long size;

    private MyBitMap (int maxBits) {
        this.data = new int[(maxBits >> shift) + 1];
    }

    public void set(int bitIndex){
        // i & mask 相当于 i % 32
        data[bitIndex>>shift] |= (1 << (bitIndex & mask));
        size++;
    }

    public void clear(int bitIndex){
        data[bitIndex >> shift] &= ~(1 << (bitIndex & mask));
    }

    public boolean test(int bitIndex){
        return (data[bitIndex>>shift] & (1 << (bitIndex & mask))) != 0;
    }

    public long size() {
        return size;
    }

    public static void main (String[] args) {

        MyBitMap myBitMap = new MyBitMap(100);
        myBitMap.set(99);
        System.out.println(myBitMap.test(99));

        int a[] = {3, 1, 4, 2, 9, 8, 0, 6, 5};
        int[] data = bitSort(a, 9);
        System.out.println(Arrays.toString(data));
    }

    public static int[] bitSort(int[] a, int max) {
        MyBitMap myBitMap = new MyBitMap(max + 1);
        for (int t : a) {
            myBitMap.set(t);
        }
        int i = 0;
        int j = 0;
        while(i <= max){
            if(myBitMap.test(i)){
                a[j++] = i;
            }
            i++;
        }
        return a;
    }
}
