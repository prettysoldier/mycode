package algorithm.string.huiwen_split;

/**
 * @author heshuaijun
 * @date 2020/2/16 16:28
 */
public class Main {

    public static void main (String[] args) {
        System.out.println(minCut("abcbacdc"));
    }

    private static int minCut(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        dp[len] = -1;
        boolean[][] flag = new boolean[len][len];
        for(int i = len - 1; i >= 0; i--){
            dp[i] = Integer.MAX_VALUE;
            for(int j = i; j < len; j++){
                if(chars[i] == chars[j] && (j - i < 2 || flag[i + 1][j - 1])){
                    flag[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }
}
