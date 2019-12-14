package algorithm;

/**
 * @author heshuaijun
 * @date 2019/12/14 22:48
 */
public class TrailingZeros {

    public static void main (String[] args) {
        System.out.println(trailingZeros(11));
    }
    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        if(n < 5){
            return 0;
        }

        long max5 = 5;
        long i = 0;
        while(max5 < n){
            max5 *= 5;
            i++;
        }

        long ret = n / 5;
        while(--i > 0){
            ret += i;
        }
        return ret;
    }
}
