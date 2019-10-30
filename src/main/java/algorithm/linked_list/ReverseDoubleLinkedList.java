package algorithm.linked_list;

import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/10/29 22:12
 */
public class ReverseDoubleLinkedList {
    public static void main (String[] args) {
        // 初始化链表
        DoubleNode head = initLinkedList();

        printLinkedList(head);

        reverse(head).setNext(null);
//        DoubleNode newHead = reverse2(head);
//        DoubleNode newHead = reverse3(head);

        printLinkedList(newHead);
    }

    private static void printLinkedList (DoubleNode newHead) {
        DoubleNode last = null;
        while(newHead != null){
            System.out.print(newHead.getValue() + " ");
            if(newHead.getNext() == null){
                last = newHead;
            }
            newHead = newHead.getNext();
        }
        System.out.println();
        while(last != null){
            System.out.print(last.getValue() + " ");

            last = last.getPrev();
        }
        System.out.println();
        System.out.println("---------");
    }

    private static DoubleNode initLinkedList () {
        DoubleNode head = new DoubleNode(1);
        DoubleNode curr = head;
        for(int i = 2; i <= 6; i++){
            DoubleNode node = new DoubleNode(i);
            curr.setNext(node);
            node.setPrev(curr);
            curr = node;
        }
        return head;
    }

    /**
     * 用递归实现
     * 缺点是需要额外的属性来存新的首节点
     */
    private static DoubleNode newHead;
    private static DoubleNode reverse(DoubleNode head){
        if(head == null){
            return null;
        }
        DoubleNode next = head.getNext();
        if(next != null){
            next = reverse(next);
            next.setNext(head);
            head.setPrev(next);
        } else{
            newHead = head;
            newHead.setPrev(null);
        }
        return head;
    }

    /**
     * 通过栈来实现
     * @param head
     * @return
     */
    private static DoubleNode reverse2(DoubleNode head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Stack<DoubleNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.getNext();
        }
        DoubleNode newHead = stack.pop();
        newHead.setPrev(null);
        DoubleNode curr = newHead;
        DoubleNode next;
        while(!stack.empty()){
            next = stack.pop();
            curr.setNext(next);
            next.setPrev(curr);
            curr = next;
        }
        curr.setNext(null);
        return newHead;
    }

    private static DoubleNode reverse3(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;

        while(head != null){
            next = head.getNext();
            head.setNext(pre);
            head.setPrev(next);
            pre = head;
            head = next;
        }
        return pre;
    }

}
