package algorithm.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个具有重复数字的列表，找出列表所有不同的排列。
 *
 * @author hsj
 * @create 2019/12/25
 */
public class RepeatPermutation {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }

        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new ArrayList<>(), results);

        return results;
    }

    /**
     * 深度优先搜索 deep first search
     *
     * @param nums
     * @param visited
     * @param permutation
     * @param results
     */
    private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
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
