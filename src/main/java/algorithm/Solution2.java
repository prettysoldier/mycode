package algorithm;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/rotate-string/description
 * 从偏移处，旋转
 * @author hsj
 * @create 2019/12/13
 */
public class Solution2 {

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

        boolean loop = offset % (str.length % offset) == 0;
        int i = 0;
        char tmp;
        char nextTmp = str[0];
        int prevIndex = 0;

        while(true){
            tmp = nextTmp;
            if(i < str.length - offset){
                i = i + offset;
            }
            else {
                i = i - str.length + offset;
            }
            if(loop && i == prevIndex){
                str[i] = tmp;
                if(i == offset - 1){
                    break;
                }
                nextTmp = str[++i];
                prevIndex++;
            }
            else {
                nextTmp = str[i];
                str[i] = tmp;
                if(i == prevIndex){
                    if(i == (str.length % offset) - 1){
                        break;
                    }
                    nextTmp = str[++i];
                    prevIndex++;
                }

            }

        }

    }


}