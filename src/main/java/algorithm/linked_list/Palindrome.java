package algorithm.linked_list;

import java.util.Stack;

/**
 * 回文结构
 *
 * @author shuaijunhe
 * @create 2019/10/31
 * @description
 */
public class Palindrome {

    public static void main(String[] args) {

        Node head = Node.initLinkedList(1, 3, 5, 4, 1);

        Node.printLinkedList(head);
        System.out.println(isSymmetryByStatic(head));
        System.out.println(isSymmetryByStatic2(head));
    }
    /**
     * 通过栈来判断是否回文
     *
     * @param head
     * @return
     */
    private static boolean isSymmetryByStatic(Node head) {
        if (head == null || head.getNext() == null) {
            return true;
        }
        int c = 1;
        Node curr = head;
        while ((curr = curr.getNext()) != null) {
            c++;
        }
        Stack<Integer> stack = new Stack<>();
        boolean even = c % 2 == 0 ? true : false;
        int i = 1;
        while (head != null) {
            if (i <= c / 2) {
                stack.push(head.getValue());
            }
            if (even && i > c / 2 && head.getValue() != stack.pop()) {
                return false;
            }
            if (!even && i > c / 2 + 1 && head.getValue() != stack.pop()) {
                return false;
            }
            head = head.getNext();
            i++;
        }
        return true;
    }

    /**
     * 通过栈来判断是否回文
     * 额外空间 O(N)
     * @param head
     * @return
     */
    private static boolean isSymmetryByStatic2(Node head) {
        if (head == null || head.getNext() == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        Node curr = head;
        while (curr != null) {
            stack.push(curr.getValue());
            curr = curr.getNext();
        }

        while(head != null){
            if(head.getValue() != stack.pop().intValue()){
                return false;
            }
            head = head.getNext();
        }
        return true;
    }
}
