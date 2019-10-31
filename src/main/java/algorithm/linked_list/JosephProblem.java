package algorithm.linked_list;

/**
 * 约瑟夫问题
 * @author shuaijunhe
 * @create 2019/10/30
 * @description
 */
public class JosephProblem {

    public static void main(String[] args) {
        josephProblem(10, 3);
    }

    /**
     * 时间复杂度：O(n * m)
     * @param size
     * @param m
     */
    private static void josephProblem(int size, int m){
        if(m > size || m < 1){
            return;
        }
        // 初始化链表
        Node head = initLinkedList(size);

        Node.printCyclicLinkedList(head, size);

        // 找出最后的节点
        Node last = head;
        while(last.getNext() != head){
            last = last.getNext();
        }

        Node prev = last;
        int phase = 0;
        Node curr = head;
        while(curr.getNext() != curr){
            if(++phase == m){
                // 删除curr节点
                prev.setNext(curr.getNext());
                phase = 0;
                System.out.println(curr.getValue() + "号自杀了");
            }
            prev = curr;
            curr = curr.getNext();
        }

        Node.printCyclicLinkedList(curr, 2);
    }

    private static Node initLinkedList (int initNum) {
        Node head = new Node(1);
        if(initNum == 1){
            head.setNext(head);
        }
        Node prev = head;
        for(int i = 2; i <= initNum; i++){
            Node node = new Node(i);
            prev.setNext(node);
            if(i == initNum){
                node.setNext(head);
            }
            prev = node;
        }
        return head;
    }

    
}
