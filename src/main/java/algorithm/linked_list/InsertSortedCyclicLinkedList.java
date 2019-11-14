package algorithm.linked_list;

/**
 * 将新的节点num插入到有序环形链表
 *
 * @author shuaijunhe
 * @create 2019/11/14
 * @description
 */
public class InsertSortedCyclicLinkedList {

    public static void main(String[] args) {

        Node head = Node.initCyclicLinkedList(1, 3, 4);

        Node.printCyclicLinkedList(insertNum(head, 5), 4);
        Node.printCyclicLinkedList(insertNum(head, 0), 4);
        Node.printCyclicLinkedList(insertNum(head, 2), 4);
    }

    private static Node insertNum(Node head, int num){
        if(head == null){
            Node n = new Node(num);
            n.setNext(n);
            return n;
        }
        Node prev = head;
        Node curr = head.getNext();
        while(curr != head){
            if(prev.getValue() <= num && num <= curr.getValue()){
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }
        Node node = new Node(num);
        prev.setNext(node);
        node.setNext(curr);
        return head.getValue() < num ? head : node;
    }
}
