package algorithm.linked_list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将搜索二叉树转换成双向链表
 * @author shuaijunhe
 * @create 2019/11/11
 * @description
 */
public class TransferBinaryTreeToLinkedList {

    public static void main(String[] args) {

        TreeNode head = buildBinaryTree();
        DoubleNode newHead = transfer(head);
        DoubleNode.printLinkedList(newHead);
    }

    /**
     * 用队列把tree存起来
     * @param head
     * @return
     */
    private static DoubleNode transfer(TreeNode head){
        Queue<TreeNode> queue = new LinkedList<>();
        // 如何把tree变成queue
        inOrderToQueue(head, queue);
        // 把queue变成双向链表
        DoubleNode prev = null;
        DoubleNode curr;
        DoubleNode newHead = null;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            curr = new DoubleNode(node.getValue());
            newHead = newHead == null ? curr : newHead;
            if(prev != null){
                prev.setNext(curr);
                curr.setPrev(prev);
            }
            prev = curr;
        }
        return newHead;
    }

    private static void inOrderToQueue(TreeNode head, Queue<TreeNode> queue){
        if(head == null){
            return;
        }
        inOrderToQueue(head.getLeft(), queue);
        queue.offer(head);
        inOrderToQueue(head.getRight(), queue);
    }

    private static TreeNode buildBinaryTree(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        node6.setLeft(node4);
        node6.setRight(node7);
        node4.setLeft(node2);
        node4.setRight(node5);
        node2.setLeft(node1);
        node2.setRight(node3);
        node7.setRight(node9);
        node9.setLeft(node8);

        return node6;
    }
}

class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode (int value) {
        this.value = value;
    }

    public int getValue () {
        return value;
    }



    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}