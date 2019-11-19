package algorithm.binary_tree;

/**
 * 用递归实现遍历二叉树：前序、中序、后序。
 * @author hsj
 * @create 2019/11/19
 */
public class IterateBinaryTreeWithRecursion {

    public static void main(String[] args) {

        BinaryTree tree = BinaryTree.initial(1, 2, 3, 4, 5, 6, 7, 8);
        IterateBinaryTreeWithRecursion.preOrderRecur(tree);
        System.out.println();
        IterateBinaryTreeWithRecursion.inOrderRecur(tree);
        System.out.println();
        IterateBinaryTreeWithRecursion.posOrderRecur(tree);
    }

    /**
     * 先序遍历：先根后左右
     * @param tree
     */
    private static void preOrderRecur(BinaryTree tree){

        if(tree == null){
            return;
        }
        System.out.print(tree.getValue() + " ");
        preOrderRecur(tree.getLeft());
        preOrderRecur(tree.getRight());
    }

    /**
     * 中序遍历：左根右
     * @param tree
     */
    private static void inOrderRecur(BinaryTree tree){

        if(tree == null){
            return;
        }
        inOrderRecur(tree.getLeft());
        System.out.print(tree.getValue() + " ");
        inOrderRecur(tree.getRight());
    }


    /**
     * 后序遍历：左右根
     * @param tree
     */
    private static void posOrderRecur(BinaryTree tree){

        if(tree == null){
            return;
        }
        posOrderRecur(tree.getLeft());
        posOrderRecur(tree.getRight());
        System.out.print(tree.getValue() + " ");
    }
}
