package algorithm.linked_list;

/**
 * 合并两个有序链表
 *
 * 自主编写首次失败，修改后失败，通过调试才成功。看书后做了优化。
 * @author shuaijunhe
 * @create 2019/11/15
 * @description
 */
public class MergeTwoSortedLinkedList {

    public static void main(String[] args) {

        Node head1 = Node.initLinkedList(1, 3, 5, 7);
        Node head2 = Node.initLinkedList(2, 4, 6, 8, 10, 12);
        Node newHead = mergeTwoSortedLinkedList(head1, head2);
        Node.printLinkedList(newHead);
    }

    /**
     * 假设是链表都是递增的
     * @param head1
     * @param head2
     * @return
     */
    private static Node mergeTwoSortedLinkedList(Node head1, Node head2){
        // 边界处理
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        Node curr1 = head1;
        Node curr2 = head2;
        Node smaller, bigger ;
        Node prev = null;
        Node newHead = null;
        // 都不为空时，进行比较
        while(curr1 != null && curr2 != null){
            // 取较小的和较大的节点
            smaller = curr1.getValue() <= curr2.getValue() ? curr1 : curr2;
            bigger = curr1.getValue() >= curr2.getValue() ? curr1 : curr2;
            // 设置前置，如果没有前置就设置新链表的头结点
            if(prev != null){
                prev.setNext(smaller);
            }else{
                newHead = smaller;
            }
            // 一定要先拿到下次循环的引用，否则会被smaller.setNext()修改后就拿不到下一个节点的引用了。
            curr1 = curr1.getNext();
            curr2 = curr2.getNext();
            // 设置大小节点之间的连接，并把较大的当做前置
            smaller.setNext(bigger);
            prev = bigger;
        }
        //
        prev.setNext(curr1 != null ? curr1 : curr2);
        return newHead;
    }
}
