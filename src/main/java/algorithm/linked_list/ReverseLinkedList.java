package algorithm.linked_list;

import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/10/29 22:12
 */
public class ReverseLinkedList {
    public static void main (String[] args) {
        // 初始化链表
        Node head = initLinkedList();

        printLinkedList(head);

//        reverse(head).setNext(null);
//        newHead = reverse2(head);
        newHead = reverse3(head);

        printLinkedList(newHead);
    }

    private static void printLinkedList (Node newHead) {
        while(newHead != null){
            System.out.print(newHead.getValue() + " ");
            newHead = newHead.getNext();
        }
        System.out.println();
    }

    private static Node initLinkedList () {
        Node head = new Node(1);
        Node curr = head;
        for(int i = 2; i <= 6; i++){
            Node node = new Node(i);
            curr.setNext(node);
            curr = node;
        }
        return head;
    }

    /**
     * 用递归实现
     * 缺点是需要额外的属性来存新的首节点
     */
    private static Node newHead;
    private static Node reverse(Node head){
        if(head == null){
            return null;
        }
        Node next = head.getNext();
        if(next != null){
            Node last = reverse(head.getNext());
            last.setNext(head);
        } else{
            newHead = head;
        }
        return head;
    }

    /**
     * 通过栈来实现
     * @param head
     * @return
     */
    private static Node reverse2(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Stack<Node> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.getNext();
        }
        Node newHead = stack.pop();
        Node curr = newHead;
        while(!stack.empty()){
            Node next = stack.pop();
            curr.setNext(next);
            curr = next;
        }
        curr.setNext(null);
        return newHead;
    }

    private static Node reverse3(Node head){
        Node pre = null;
        Node next = null;

        while(head != null){
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

}
