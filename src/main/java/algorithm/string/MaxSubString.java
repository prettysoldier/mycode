package algorithm.string; /**
 * @author heshuaijun
 * @date 2020/2/14 21:14
 */

import java.util.HashSet;
import java.util.Set;

/**
 * @author heshuaijun
 * @date 2020/2/14 20:57
 */
public class MaxSubString {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String str = "Hello World!";
        Set<Character> set = new HashSet<>();
        int start = 0;
        int end = 0;
        int maxStart = 0;
        int maxEnd = 0;
        for(int i = 0; i < str.length(); i++ ){
            char c = str.charAt(i);
            if(set.contains(c)){
                for(Character tmp : set){
                    if(tmp == c){
                        start = str.indexOf(c, start) + 1;
                        break;
                    }
                }
            }else{
                end++;
            }
            set.add(c);
            if(end - start > maxEnd - maxStart){
                maxEnd = end;
                maxStart = start;
            }
        }
        if(set.size() == 0){
            return;
        }
        System.out.println(str.substring(maxStart, maxEnd));
    }
}
