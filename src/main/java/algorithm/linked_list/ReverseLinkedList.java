package algorithm.linked_list;

import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/10/29 22:12
 */
public class ReverseLinkedList {
    public static void main (String[] args) {
        // 初始化链表
        Node head = Node.initLinkedList(6);

        Node.printLinkedList(head);

//        reverse(head).setNext(null);
//        newHead = reverse2(head);
//        newHead = reverse3(head);
        /**
         * 部分反转
         */
        newHead = reversePart(head, 2, 4);
        Node.printLinkedList(newHead);
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

    public static Node reverse3(Node head){
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

    /**
     *
     * @param head
     * @param begin
     * @param end
     * @return
     */
    private static Node reversePart(Node head, int begin, int end){

        if( end - begin < 1){
            return head;
        }
        if ( begin < 1) {
            return head;
        }
        Node newHead = head;
        Node pre = null;
        int curr = 0;
        Node next = null;
        /**
         * 先循环一遍，找出3个边界节点
         */
        Node subBegin = null;
        Node subBeginPrev = null;
        Node subEndNext = null;
        Node currNode = head;
        while(currNode != null){
            curr++;
            next = currNode.getNext();
            if(curr == begin){
                subBeginPrev = pre;
                subBegin = currNode;
            }
            if(curr == end){
                subEndNext = next;
            }
            pre = currNode;
            currNode = next;
        }

        if(end > curr){
            return newHead;
        }

        Node[] ret = reversePartInner(subBegin, end - begin + 1);
        if(subBeginPrev != null){
            subBeginPrev.setNext(ret[0]);
        }else{
            newHead = ret[0];
        }
        ret[1].setNext(subEndNext);

        return newHead;
    }

    private static Node[] reversePartInner(Node head, int count){

        Node h = head;
        Node pre = null;
        Node next = null;
        int i = 1;
        while(head != null && i <= count){
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
            i++;
        }
        Node[] ret = new Node[2];
        ret[0] = pre;
        ret[1] = h;
        return ret;
    }


}
