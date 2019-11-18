package algorithm.linked_list;

import java.util.Stack;

/**
 * 用链表表示两个整数，将这两个整数相加，生成一个新的链表
 *
 * 1.如果将链表转换成整数，在用整数的相加，会有溢出的问题。
 * 2.直接用链表模拟整数的相加过程。
 *
 * @author shuaijunhe
 * @create 2019/11/1
 * @description
 */
public class AddOperatorWithLikedList {

    public static void main(String[] args) {

        Node head1 = Node.initLinkedList(1, 2 , 3, 7);
        Node head2 = Node.initLinkedList(6, 3);

//        Node head = add2(head1, head2);
        Node head = addAdvance(head1, head2);

        Node.printLinkedList(head);
    }

    /**
     * 这是我自己实现的
     * @param head1
     * @param head2
     * @return
     */
    private static Node add(Node head1, Node head2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node curr1 = head1;
        int count1 = 0;
        while(curr1 != null){
            count1++;
            stack1.push(curr1);
            curr1 = curr1.getNext();
        }
        Node curr2 = head2;
        int count2 = 0;
        while(curr2 != null){
            count2++;
            stack2.push(curr2);
            curr2 = curr2.getNext();
        }
        Stack<Node> biggerStack;
        Stack<Node> smallerStack;
        if(count1 >= count2){
            biggerStack = stack1;
            smallerStack = stack2;
        } else{
            biggerStack = stack2;
            smallerStack = stack1;
        }
        Node next = null;
        Node currNew = null;
        // 是否进位
        boolean isCarryBit = false;
        while(!biggerStack.isEmpty()){
            Node curr = biggerStack.pop();
            if(smallerStack.empty()){
                if(isCarryBit){
                    if(curr.getValue() == 9){
                        currNew = new Node(0);
                        currNew.setNext(next);
                        next = currNew;
                    }else{
                        isCarryBit = false;
                        currNew = new Node(curr.getValue() + 1);
                        currNew.setNext(next);
                        next = currNew;
                    }
                }else{
                    currNew = new Node(curr.getValue());
                    currNew.setNext(next);
                    next = currNew;
                }
                continue;
            }
            Node smallCurr = smallerStack.pop();
            int add = curr.getValue() + smallCurr.getValue();
            add = isCarryBit ? add + 1 : add;
            if(add < 10){
                currNew = new Node(add);
            }else{
                isCarryBit = true;
                currNew = new Node(add % 10);
            }
            currNew.setNext(next);
            next = currNew;
        }
        if(isCarryBit){
            Node head = new Node(1);
            head.setNext(currNew);
            return head;
        }
        return currNew;
    }

    /**
     * 这是标准答案
     * @param head1
     * @param head2
     * @return
     */
    private static Node add2(Node head1, Node head2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node curr1 = head1;
        while(curr1 != null){
            stack1.push(curr1);
            curr1 = curr1.getNext();
        }
        Node curr2 = head2;
        while(curr2 != null){
            stack2.push(curr2);
            curr2 = curr2.getNext();
        }

        int carryBit = 0;
        int n1, n2, n;
        Node prev = null;
        Node curr = null;
        while(!(stack1.isEmpty() && stack2.isEmpty())){
            n1 = stack1.isEmpty() ? 0 : stack1.pop().getValue();
            n2 = stack2.isEmpty() ? 0 : stack2.pop().getValue();
            n = n1 + n2 + carryBit;
            carryBit = n / 10;
            curr = new Node(n % 10);
            curr.setNext(prev);
            prev = curr;
        }
        if(carryBit == 1){
            curr = new Node(1);
            curr.setNext(prev);
        }
        return curr;
    }

    /**
     * 节省栈的空间的方法：通过逆序链表实现
     * @param head1
     * @param head2
     * @return
     */
    private static Node addAdvance(Node head1, Node head2){
        head1 = ReverseLinkedList.reverse3(head1);
        head2 = ReverseLinkedList.reverse3(head2);

        int n1, n2, n;
        int carryBit = 0;
        Node curr = null;
        Node prev = null;
        while(head1 != null || head2 != null){
            n1 = head1 == null ? 0 : head1.getValue();
            n2 = head2 == null ? 0 : head2.getValue();
            n = n1 + n2 + carryBit;
            carryBit = n / 10;
            curr = new Node(n % 10);
            curr.setNext(prev);
            prev = curr;

            head1 = head1 == null ? null : head1.getNext();
            head2 = head2 == null ? null : head2.getNext();
        }
        if(carryBit == 1){
            curr = new Node(1);
            curr.setNext(prev);
        }
        return curr;
    }
}
