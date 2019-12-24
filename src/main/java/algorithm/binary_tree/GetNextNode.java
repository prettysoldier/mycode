package algorithm.binary_tree;

/**
 * 获取后继节点
 * @author hsj
 * @create 2019/12/24
 */
public class GetNextNode {

    public static BinaryTree getNextNode(BinaryTree node){
        if(node == null){
            return null;
        }
        // 有右子数：右子树的最左节点
        if(node.right != null){
            BinaryTree right = node.right;

            while(right.left != null){
                right = right.left;
            }
            return right;
        }
        // 没有右子数，是父节点的左子节点，父节点即是后继。
        BinaryTree parent = node.parent;
        while(parent != null && parent.left != node){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
}
