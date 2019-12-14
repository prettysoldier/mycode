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
        rotateString(str, 1000000000);
        System.out.println(Arrays.toString(str));

    }
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public static void rotateString(char[] str, int offset) {
        // write your code here
        if(str == null || str.length == 0){
            return;
        }
        offset = offset % str.length;
        if(offset == 0){
            return;
        }
        int i = 0;
        char tmp;
        char nextTmp = str[0];
        int prevIndex = 0;

        for(int count = 0;count < str.length; count++){
            tmp = nextTmp;
            if(i < str.length - offset){
                i = i + offset;
            }
            else {
                i = i - str.length + offset;
            }
            if(i == prevIndex){
                str[i] = tmp;
                nextTmp = str[++i];
                prevIndex++;
            } else {
                nextTmp = str[i];
                str[i] = tmp;
            }
        }
    }

    public void rotateString2(char[] str, int offset) {
        if (str == null || str.length == 0)
            return;

        offset = offset % str.length;
        reverse(str, 0, str.length - offset - 1);
        reverse(str, str.length - offset, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    private void reverse(char[] str, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }

    }

}