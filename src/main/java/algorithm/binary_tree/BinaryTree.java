package algorithm.binary_tree;

import java.util.LinkedList;

/**
 * @author hsj
 * @create 2019/11/19
 */
public class BinaryTree {

    private int value;

    private BinaryTree left;

    private BinaryTree right;

    static BinaryTree initial(int... values){
        if(values == null || values.length == 0){
            return null;
        }
        BinaryTree root = new BinaryTree(values[0]);
        LinkedList<BinaryTree> parentList = new LinkedList<>();
        parentList.add(root);
        boolean left = true;
        for(int i = 1; i < values.length; i++){

            BinaryTree tree = new BinaryTree(values[i]);
            if(left){
                parentList.getFirst().setLeft(tree);
                left = false;
                parentList.add(tree);
            }else{
                parentList.remove().setRight(tree);
                left = true;
                parentList.add(tree);
            }
        }
        return root;
    }

    /**
     * 高度的定义为：从结点x向下到某个叶结点最长简单路径中边的条数
     * @param root
     * @return
     */
    public static int getHeight(BinaryTree root){
        if(root == null){
            return 0;
        }
        int left = getHeight(root.getLeft());
        int right = getHeight(root.getRight());

        return Math.max(left, right) + 1;
    }

    public BinaryTree(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
