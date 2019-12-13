package algorithm;

import java.util.Arrays;

/**
 * 从偏移处，左右互换
 * @author hsj
 * @create 2019/12/13
 */
public class Solution {

    public static void main(String[] args) {
        char[] str = new char[]{'0', '1', '2', '3', '4', '5', '6'};
        rotateString(str, 2);
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

        int i = 0;
        char tmp;
        char nextTmp = str[i];
        int nextIndex = 0;

        do{
            tmp = nextTmp;
            if(i < offset){
                nextIndex = str.length - offset + i;
            }
            else if(i > offset){
                nextIndex = i - offset - 1;
            }
            else if(i == offset){
                nextIndex = str.length - offset - 1;
            }

            nextTmp = str[nextIndex];
            str[nextIndex] = tmp;
            i = nextIndex;
        }while(i != 0);

    }


}