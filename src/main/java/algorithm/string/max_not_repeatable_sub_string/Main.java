package algorithm.string.max_not_repeatable_sub_string;

/**
 * @author heshuaijun
 * @date 2020/2/14 22:42
 */
public class Main {

    public static void main (String[] args) {
        System.out.println(maxSub("Hello Wroldadqefsdrrggffrrhyyjyukfvcvny"));
    }

    private static String maxSub(String str){
        if(str == null || str.length() <= 1){
            return str;
        }
        // 动态规划:dp[i]存最后一次出现第i个字符的位置
        int[] dp = new int[256];
        for(int i = 0; i < dp.length; i++){
            dp[i] = -1;
        }

        char[] chars = str.toCharArray();
        int maxStart = 0;
        int maxEnd = 0;
        int start = -1;
        for(int i = 0; i < chars.length; i++){
            start = Math.max(start, dp[chars[i]]);
            if(i - start> maxEnd - maxStart){
                maxStart = start + 1;
                maxEnd = i + 1;
            }
            dp[chars[i]] = i;
        }
        return str.substring(maxStart, maxEnd);
    }
}
