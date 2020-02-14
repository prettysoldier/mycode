package algorithm.string.max_not_repeatable_sub_string;

/**
 * @author heshuaijun
 * @date 2020/2/14 22:42
 */
public class Main {

    public static void main (String[] args) {
        System.out.println(getSub("Hello Wrold"));
    }

    private static String getSub(String str){
        if(str == null || str.length() == 0){
            return "";
        }
        if(str.length() == 1){
            return str;
        }
        int[] map = new int[256];
        for(int i = 0; i < map.length; i++){
            map[i] = -1;
        }
        int pre = -1;
        int len = 0;
        char[] chars = str.toCharArray();
        int cur = 0;
        int start = 0;
        for(int i = 0; i < chars.length; i++){
            pre = Math.max(pre, map[chars[i]]);
            cur = i - pre;
            if(cur > len){
                start = pre + 1;
                len = cur;
            }
            map[chars[i]] = i;
        }
        return str.substring(start, start + len);
    }
}
