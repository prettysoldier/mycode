package algorithm.binary_tree;

import java.util.Stack;

/**
 * 用非递归实现遍历二叉树：前序、中序、后序。
 * @author hsj
 * @create 2019/11/19
 */
public class IterateBinaryTreeWithoutRecursion {

    public static void main(String[] args) {

        BinaryTree tree = BinaryTree.initial(1, 2, 3, 4, 5, 6, 7, 8);
        IterateBinaryTreeWithoutRecursion.preOrderRecur(tree);
        System.out.println();
        IterateBinaryTreeWithoutRecursion.inOrderRecur(tree);
        System.out.println();
        IterateBinaryTreeWithoutRecursion.posOrderRecur(tree);
    }

    /**
     * 先序遍历：先根后左右
     * @param tree
     */
    private static void preOrderRecur(BinaryTree tree){

        if(tree == null){
            return;
        }
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()){

            BinaryTree curr = stack.pop();
            System.out.print(curr.getValue() + " ");
            if(curr.getRight() != null){
                stack.push(curr.getRight());
            }
            if(curr.getLeft() != null){
                stack.push(curr.getLeft());
            }
        }
    }

    /**
     * 中序遍历：左根右
     * @param root
     */
    private static void inOrderRecur(BinaryTree root){

        if(root == null){
            return;
        }
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree curr = root;

        while(!stack.isEmpty() || curr != null){

            if(curr != null){
                // 把所有左边界压栈
                stack.push(curr);
                curr = curr.getLeft();
            } else{
                // 当左节点为null时，弹出并输出，然后指向有节点。
                curr = stack.pop();
                System.out.print(curr.getValue() + " ");
                curr = curr.getRight();
            }
        }
    }


    /**
     * 后序遍历：左右根
     * 先压入root, 进入循环：弹出，并依次压入左右，直到栈为空。
     * 每个弹出的元素 进入 第二个栈，最后遍历栈。
     * @param root
     */
    private static void posOrderRecur(BinaryTree root){

        if(root == null){
            return;
        }
        // 定义两个栈
        Stack<BinaryTree> stack1 = new Stack<>();
        Stack<BinaryTree> stack2 = new Stack<>();
        stack1.push(root);
        BinaryTree curr;
        while(!stack1.isEmpty()){
            curr = stack1.pop();
            stack2.push(curr);

            if(curr.getLeft() != null){
                stack1.push(curr.getLeft());
            }
            if(curr.getRight() != null){
                stack1.push(curr.getRight());
            }
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().getValue() + " ");
        }
    }

    private static void posOrderRecur2(BinaryTree root){

        if(root == null){
            return;
        }
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(root);
        BinaryTree lastPrint = root;
        BinaryTree top;

        while(!stack.isEmpty()){
            top = stack.peek();
            if(top.getLeft() != null && lastPrint != top.getLeft() && lastPrint != top.getRight()){
                stack.push(top.getLeft());
            }else if(top.getRight() != null && lastPrint != top.getRight()){
                stack.push(top.getRight());
            }else{
                top = stack.pop();
                System.out.print(top.getValue() + " ");
                lastPrint = top;
            }

        }
    }
}
