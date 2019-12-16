package algorithm.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历+范围判断
 * @author hsj
 * @create 2019/12/16
 */
public class SearchRangeInBinarySearchTree {

    public static void main(String[] args) {

        BinaryTree root = SerializationAndDeserialization.deserialize("20,8,4,#,#,12,#,#,22,#,#");
        System.out.println(searchRange(root, 10, 20));
    }

    public static List<Integer> searchRange(BinaryTree root, int k1, int k2) {
        // write your code here
        List<Integer> ret = new ArrayList<>();
        if(root == null || k1 > k2){
            return ret;
        }

        inner(root, k1, k2, ret);
        return ret;
    }

    public static void inner(BinaryTree root, int k1, int k2, List<Integer> ret){
        if(root == null){
            return;
        }
        if(root.value > k1){
            inner(root.left, k1, k2, ret);
        }
        if(root.value >= k1 && root.value <= k2){
            ret.add(root.value);
        }
        if(root.value < k2){
            inner(root.right, k1, k2, ret);
        }
    }
}
