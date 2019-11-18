package algorithm.linked_list;

/**
 * 把链表分为左右链表，然后一左一右合并起来
 * @author shuaijunhe
 * @create 2019/11/18
 * @description
 */
public class RelocateLinkedList {

    public static void main(String[] args) {

        Node head = Node.initLinkedList(1, 2, 3, 4, 5, 6, 7);
        relocate(head);
        Node.printLinkedList(head);

    }

    private static void relocate(Node head){
        if(head == null || head.getNext() == null){
            return;
        }
        // 如何找到链表的中间值?
        // mid每走1步，right走两步
        Node mid = head;
        Node right = head.getNext();
        while(right.getNext() != null && right.getNext().getNext() != null){
            mid = mid.getNext();
            right = right.getNext().getNext();
        }
        right = mid.getNext();
        mid.setNext(null);

        // 左右merge
        Node left = head;
        Node lnext, rnext;
        while(left.getNext() != null){

            lnext = left.getNext();
            rnext = right.getNext();

            right.setNext(lnext);
            left.setNext(right);

            left = lnext;
            right = rnext;

        }
        left.setNext(right);
    }

}
