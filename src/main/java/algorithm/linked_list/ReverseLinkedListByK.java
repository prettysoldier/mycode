package algorithm.linked_list;

import java.util.Stack;

/**
 * 将单链表的每K个节点之间逆序
 * @author heshuaijun
 * @date 2019/10/29 22:12
 */
public class ReverseLinkedListByK {
    static Node newHead;
    public static void main (String[] args) {
        // 初始化链表
        Node head = Node.initLinkedList(11);

        Node.printLinkedList(head);

//        newHead = reverse(head, 3);
        newHead = reverse3(head, 3);

        Node.printLinkedList(newHead);
    }



    /**
     * 通过栈来实现
     * @param head
     * @return
     */
    private static Node reverse(Node head, int k){
        if(head == null || head.getNext() == null || k < 2){
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node curr = head;
        boolean first = true;
        Node newHead = null;
        Node subHead = null;
        int i = 0;
        while(curr != null){

            if(i++ < k){
                stack.push(curr);
                curr = curr.getNext();
                continue;
            }

            subHead = subHead == null ? stack.pop() : subHead;
            if(first){
                newHead = subHead;
                first = false;
            }

            while(!stack.empty()){
                Node next = stack.pop();
                subHead.setNext(next);
                subHead = next;
            }

            stack.push(curr);
            curr = curr.getNext();
            i = 1;
        }
        while(!stack.empty()){
            Node next = stack.pop();
            subHead.setNext(next);
            subHead = next;
        }
        subHead.setNext(null);
        return newHead;
    }

    public static Node reverse3(Node head, int k){
        if(head == null || head.getNext() == null || k < 2){
            return head;
        }

        Node curr = head;
        Node newHead = null;
        // 下一段的前置
        Node subPrev = null;
        Node subPrev2 = null;
        int i = 1;

        Node pre = null;
        Node next = null;
        int flag = 1;
        Node subHead = null;
        Node subTail = null;

        while(curr != null){

            if(i <= k){
                next = curr.getNext();
                if(i == 1 ){
                    subHead = curr;
                    // 保留每轮的第一个节点，当做下一轮节点的前置
                    if(flag % 4 == 1){
                        subPrev = curr;
                    }else{
                        subPrev2 = curr;
                    }
                    flag++;
                }else{
                    curr.setNext(pre);
                }
                // 把第一轮的head，当做新的head
                if(i == k){
                    if(flag % 4 == 0){
                        subPrev.setNext(curr);
                        subPrev = null;
                    }else if(flag != 2){
                        subPrev2.setNext(curr);
                        subPrev2 = null;
                    }
                    flag++;
                    newHead = newHead == null ? curr : newHead;
                }
                subTail = curr;
                pre = curr;
                curr = next;
                i++;
            }
            else{
                i = 1;
            }
        }
        subPrev = subPrev == null ? subPrev2 : subPrev;
        if(subTail == subHead){
            subPrev.setNext(subHead);
        }else{
            subPrev.setNext(subTail);
            subHead.setNext(null);
        }
        return newHead;
    }
}
