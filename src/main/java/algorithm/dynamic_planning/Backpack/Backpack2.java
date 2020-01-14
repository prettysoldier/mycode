package algorithm.dynamic_planning.Backpack;

import java.util.ArrayList;
import java.util.List;

/**
 * 125. 背包问题 II ：0/1背包问题
 * 中文English
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 *
 * 问最多能装入背包的总价值是多大?
 *
 * 样例
 * 样例 1:
 *
 * 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
 * 输出: 9
 * 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
 * 样例 2:
 *
 * 输入: m = 10, A = [2, 3, 8], V = [2, 5, 8]
 * 输出: 10
 * 解释: 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
 * 挑战
 * O(nm) 空间复杂度可以通过, 不过你可以尝试 O(m) 空间复杂度吗?
 *
 * 注意事项
 * A[i], V[i], n, m 均为整数
 * 你不能将物品进行切分
 * 你所挑选的要装入背包的物品的总大小不能超过 m
 * 每个物品只能取一次
 *
 */
public class Backpack2 {


    public static int backPack(int m, int[] A, int[] V) {

        // 用来存每个容量的最大价值
        int[] f = new int[m + 1];
        for(int i = 0; i < A.length; i++){

            for(int j = m; j >= A[i]; j--){
                f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }

    public static int backPackWithPath(int m, int[] A, int[] V) {

        // 用来存每个容量的最大价值
        int[] f = new int[m + 1];
        int[] s = new int[m + 1];
        for(int i = 0; i < m + 1; i++){
            s[i] = -1;
        }
        for(int i = 0; i < A.length; i++){

            for(int j = m; j >= A[i]; j--){
                if(f[j - A[i]] + V[i] > f[j]){
                    f[j] = f[j - A[i]] + V[i];
                    s[j] = i;
                }

            }
        }
        int n = m;
        List<Integer> list = new ArrayList<>();
        while(n > 0){
            if(s[n] < 0){
                break;
            }
            list.add(s[n]);
            n -= A[s[n]];
        }
        return f[m];
    }

    public int backPackII(int m, int[] A, int V[]) {

        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= A.length; i++) {

            for (int j = 1; j <= m; j++) {
                if (A[i - 1] > j) {
                    // 装不下时，用上一行的数据
                    dp[i][j] = dp[(i - 1)][j];
                } else {
                    // 能装下时，比较上行和装下时大小
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[(i - 1)][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[A.length][m];
    }
}
