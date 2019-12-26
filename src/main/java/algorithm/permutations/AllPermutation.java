package algorithm.permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * @author hsj
 * @create 2019/12/26
 */
public class AllPermutation {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 1}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }

        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

        return results;
    }

    private static void dfs(int[] nums, boolean[] visited, List<Integer> permutation,
                     List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
