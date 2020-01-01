package algorithm;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/rotate-string/description
 * 从偏移处，旋转
 * @author hsj
 * @create 2019/12/13
 */
public class RotateString {

    public static void main(String[] args) {
        char[] str = "timelimiterror".toCharArray();
        rotateString4(str, 3);
        System.out.println(Arrays.toString(str));

    }
    /**
     * @param chars: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public static void rotateString(char[] chars, int offset) {
        // write your code here
        if(chars == null || chars.length == 0){
            return;
        }
        offset = offset % chars.length;
        if(offset == 0){
            return;
        }
        int i = 0;
        char tmp;
        char nextTmp = chars[0];
        int prevIndex = 0;

        for(int count = 0;count < chars.length; count++){
            tmp = nextTmp;
            if(i < chars.length - offset){
                i = i + offset;
            }
            else {
                i = i - chars.length + offset;
            }
            if(i == prevIndex){
                chars[i] = tmp;
                nextTmp = chars[++i];
                prevIndex++;
            } else {
                nextTmp = chars[i];
                chars[i] = tmp;
            }
        }
    }

    /**
     * 三次翻转
     * @param str
     * @param i 要旋转的次数（左旋）
     */
    public void rotateString2(char[] str, int i) {
        if (str == null || str.length == 0)
            return;

        i = i % str.length;
        reverse(str, 0, i -1);
        reverse(str, i, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    private void reverse(char[] str, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }

    }

    /**
     * “杂技”算法
     * @param str
     * @param move
     */
    public static void rotateString3(char[] str, int move) {
        if (str == null || str.length == 0)
            return;

        int count = str.length;
        move = move % count;
        char tmp;
        int j;
        int k;
        int gcd = getGcd(count, move);
        for(int i = 0; i < gcd; i++){
            tmp = str[i];
            j = i;
            while(true){
                k = j + move;
                if(k >= count){
                    k -= count;
                }
                if(k == i){
                    break;
                }
                str[j] = str[k];
                j = k;
            }
            str[j] = tmp;

        }

    }

    /**
     * 求最大公约数：辗转相减法
     * greatest common divisor
     * @return
     */
    public static int getGcd(int i, int j){
        while (i != j){
            if( i > j){
                i -= j;
            }
            else {
                j -= i;
            }
        }
        return i;
    }

    /**
     * 迭代替换算法：a b -> a b1 b2 -> b2 b1 a -> b1 b2 a (即b a)
     * 没懂！TODO
     * 此算法的速度是最快的！
     * @param str
     * @param move
     */
    public static void rotateString4(char[] str, int move) {
        if (str == null || str.length == 0){
            return;
        }
        int i = move;
        int j = str.length - i;
        int p = i;
        while( i != j){
            if(i > j){
                swap(str, p - i, p, j);
                i -= j;
            }else{
                swap(str, p - i, p+j-i, i);
                j -= i;
            }
        }
        swap(str,p - i, p, i);
    }

    /**
     * 互换 arr[a...a+m-1] 与 arr[b...b+m-1]
     * @param arr
     * @param a
     * @param b
     * @param m
     */
    private static void swap(char[] arr, int a, int b, int m){
        char tmp;
        for(int i = 0; i < m; i++){
            tmp = arr[a + i];
            arr[a + i] = arr[b + i];
            arr[b + i] = tmp;
        }
    }
}