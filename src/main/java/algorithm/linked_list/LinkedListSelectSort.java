package algorithm.linked_list;

/**
 * 单链表的选择排序
 * 选择排序：不断从未排序的集合中返回最小节点，放入已排序容器中的末尾
 * 此题特点：需要获取未排序最小节点的前置，用来把原单链表连接起来。
 * @author shuaijunhe
 * @create 2019/11/12
 * @description
 */
public class LinkedListSelectSort {

    public static void main(String[] args) {

        // 初始化链表
        Node head = Node.initLinkedList(3, 5, 2, 4, 1);

        Node.printLinkedList(selectSort(head));


    }

    private static Node selectSort(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node curr = head;
        Node small;
        // 排序部分的结尾
        Node tail = null;
        while(curr != null){
            small = curr;
            Node smallPrev = getSmallestNodePrev(curr);
            if(smallPrev != null){
                small = smallPrev.getNext();
                smallPrev.setNext(small.getNext());
            }
            if(tail == null){
                head = small;
            }else{
                tail.setNext(small);
            }
            tail = small;
            curr = curr == small ? curr.getNext() : curr;
        }

        return head;
    }

    /**
     * 获取最小节点的前置
     * @param head
     * @return 如果最小节点是头结点，则返回null
     */
    private static Node getSmallestNodePrev(Node head){

        Node small = head;
        Node smallPrev = null;
        Node prev = head;
        Node curr = head.getNext();
        while(curr != null){
            if(curr.getValue() < small.getValue()){
                smallPrev = prev;
                small = curr;
            }
            prev = curr;
            curr = curr.getNext();
        }

        return smallPrev;
    }
}
