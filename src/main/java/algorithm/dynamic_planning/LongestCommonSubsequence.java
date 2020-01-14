package algorithm.dynamic_planning;

/**
 * 描述
 * 中文
 * English
 * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
 *
 * 您在真实的面试中是否遇到过这个题？
 * 说明
 * 最长公共子序列的定义：
 *
 * 最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 * 样例
 * 样例 1:
 * 	输入:  "ABCD" and "EDCA"
 * 	输出:  1
 *
 * 	解释:
 * 	LCS 是 'A' 或  'D' 或 'C'
 *
 *
 * 样例 2:
 * 	输入: "ABCD" and "EACB"
 * 	输出:  2
 *
 * 	解释:
 * 	LCS 是 "AC"
 *
 * @author hsj
 * @create 2020/1/14
 */
public class LongestCommonSubsequence {

    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {

        int dp[][] = new int[A.length() + 1][A.length() + 1];

        for(int i = 1; i <= A.length(); i++){
            for(int j = 1; j <= A.length(); j++){

                if(A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[A.length()][B.length()];
    }
}
