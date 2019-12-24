package algorithm;

import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author heshuaijun
 * @date 2019/12/14 0:48
 */
public class T {
    public static void main (String[] args) {
//        System.out.println(Arrays.toString(getNarcissisticNumbers(3).toArray()));
//        reverseWords("  Life  doesn't  always    give     us  the       joys we want.");
        firstUniqChar("abaccdeff");


    }
    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        if(n == 0){
            return list;
        }
        if(n == 1){
            for(int i = 0; i <=9 ; i++){
                list.add(i);
            }
            return list;
        }
        int c = 0;

        for(int i= (int)Math.pow(10, n - 1);i< (int)Math.pow(10, n);i++){
            c = 0;
            for(int j = 0; j< n; j++){
                c += (int)Math.pow((i/(int)Math.pow(10, j)) % 10, n);
            }


            if(i == c){
                list.add(i);
            }
        }
        return list;
    }

    public int aplusb(int a, int b) {
        // write your code here
        if(a == b){
            return (a % 3 == 0) ? a : 0;
        }
        int start = a < b ? a : b;
        int end = a < b ? b : a;
        int ret = 0;
        for(int i = start; i <= end; i++){
            if(i % 3 == 0){
                ret += i;
            }
        }
        return ret;
    }

    public static String reverseWords(String s) {
        // write your code here
        String split = " ";
        String[] words = s.split(split);
        if(words.length == 0){
            return "";
        }
        Stack<String> stack = new Stack<String>();
        for(String w : words){
            if(w.trim().equals("")){
                continue;
            }
            stack.push(w);
        }
        if(stack.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            String w = stack.pop();
            sb.append(w).append(split);
        }
        return sb.substring(0, sb.length() - 1);
    }

    public int strStr(String source, String target) {
        // Write your code here
        return source.indexOf(target);
    }

    public static char firstUniqChar(String str) {
        // Write your code here

        if(str.length() == 1){
            return str.toCharArray()[0];
        }
        TreeMap<Character, Integer> tm = new TreeMap<>();
        char[] chars = str.toCharArray();
        for(char c : chars){
            int n = tm.get(c) == null ? 0 : tm.get(c);
            tm.put(c, n + 1);
        }
        Character ret = null;
        while(tm.size() > 0){
            Map.Entry<Character, Integer> entry = tm.pollFirstEntry();
            if(entry.getValue() == 1){
                ret = entry.getKey();
            }
        }

        return ret;
    }
}
