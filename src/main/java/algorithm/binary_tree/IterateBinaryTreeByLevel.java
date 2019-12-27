package algorithm.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次排序
 * @author hsj
 * @create 2019/11/19
 */
public class IterateBinaryTreeByLevel {

    public static void main(String[] args) {

        BinaryTree tree = BinaryTree.initial(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(IterateBinaryTreeByLevel.levelOrderByQueue(tree));
        System.out.println(IterateBinaryTreeByLevel.levelOrderByDfs(tree));


    }


    public static List<List<Integer>> levelOrderByQueue(BinaryTree root){

        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        BinaryTree cur;
        List<Integer> line;
        while(!queue.isEmpty()){
            line = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                cur = queue.poll();
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
                line.add(cur.value);
            }
            result.add(line);
        }
        return result;
    }

    /**
     * 深度优先算法
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderByDfs(BinaryTree root){

        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        int maxLevel = 0;
        while (true) {
            List<Integer> level = new ArrayList<>();
            dfs(root, level, 0, maxLevel);
            if (level.size() == 0) {
                break;
            }
            results.add(level);
            maxLevel++;
        }

        return results;
    }

    private static void dfs(BinaryTree root, List<Integer> levelResult, int curtLevel, int maxLevel) {
        if (root == null || curtLevel > maxLevel) {
            return;
        }

        if (curtLevel == maxLevel) {
            levelResult.add(root.value);
            return;
        }

        dfs(root.left, levelResult, curtLevel + 1, maxLevel);
        dfs(root.right, levelResult, curtLevel + 1, maxLevel);
    }
}
