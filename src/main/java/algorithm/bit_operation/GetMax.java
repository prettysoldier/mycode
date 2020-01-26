package algorithm.bit_operation;

/**
 * @author heshuaijun
 * @date 2020/1/25 23:09
 */
public class GetMax {

    /**
     * 有溢出问题，a-b溢出！
     * @param a
     * @param b
     * @return
     */
    private static int getMax(int a, int b){
        int c = a - b;
        int t1 = c >>> 31;
        int t2 = t1 ^ 1;
        return b * t1 + a * t2;
    }

    public static void main (String[] args) {
        System.out.println(getMax(10, 20));
        System.out.println(getMax(10, 9));
        System.out.println(getMax(-2, -1));

    }


}
