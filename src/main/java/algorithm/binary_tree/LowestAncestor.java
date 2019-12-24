package algorithm.binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class LowestAncestor {

    public static BinaryTree lowestAncestor(BinaryTree node, BinaryTree o1, BinaryTree o2){
        if(node == null || node == o1 || node == o2){
            return node;
        }
        BinaryTree left = lowestAncestor(node.left, o1, o2);
        BinaryTree right = lowestAncestor(node.right, o1, o2);
        if(left != null && right != null){
            return node;
        }
        return left != null ? left : right;
    }


}
