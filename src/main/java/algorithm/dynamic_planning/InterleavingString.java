package algorithm.dynamic_planning;

/**
 * 中文English
 * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
 *
 * 样例
 * 样例 1：
 *
 * 输入:
 * "aabcc"
 * "dbbca"
 * "aadbbcbcac"
 * 输出:
 * true
 * 样例 2：
 *
 * 输入:
 * ""
 * ""
 * "1"
 * 输出:
 * false
 * 样例 3：
 *
 * 输入:
 * "aabcc"
 * "dbbca"
 * "aadbbbaccc"
 * 输出:
 * false
 * 挑战
 * 要求时间复杂度为O(n2)或者更好
 *
 * @author hsj
 * @create 2020/1/14
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // 动态规划表：s1的前i个字母和s2的前j个字母是否能构成当前i+j个字母。
        // 状态转移：看第i+j+1个是否能被s1的第i+1个构成或被s2的第j+1个构成。
        //         第i+j+1个是被s1的前i+1和s2的前j个构成，或者被s1的前i个和s2的前j+1个构成，如果都不能构成，直接返回false。
        boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
        interleaved[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0]){

                interleaved[i][0] = true;
            }
        }

        for (int j = 1; j <= s2.length(); j++) {
            if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1]){

                interleaved[0][j] = true;
            }
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                        || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1])){

                    interleaved[i][j] = true;
                }

            }
        }

        return interleaved[s1.length()][s2.length()];
    }


}
