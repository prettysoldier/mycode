package algorithm.linked_list;

/**
 * 约瑟夫问题
 * @author shuaijunhe
 * @create 2019/10/30
 * @description
 */
public class JosephProblem {

    public static void main(String[] args) {
        // 初始化链表
        int size = 15;
        Node head = initCyclicLinkedList(size);

        Node.printCyclicLinkedList(head, size);

//        Node live = josephProblem(head, size, 3);
        Node live = josephProblemAdvance(head,3);

        System.out.println(live.getValue());
    }

    /**
     * 进阶解法，没有理解！！
     * 难度：3
     */
    private static Node josephProblemAdvance(Node head, int m){
        if(head == null || head.getNext() == head || m < 1){
            return head;
        }

        // 获取链表大小
        Node cur = head.getNext();
        int tmp = 1;
        while(cur != head){
            tmp++;
            cur = cur.getNext();
        }
        // 核心方法：获取存活节点的位置！
        tmp = getLive(tmp, m);

        // 获取tmp位的节点
        while(--tmp != 0){
            head = head.getNext();
        }
        return head;
    }

    private static int getLive(int i, int m){
        if(i == 1){
            return 1;
        }
        int live = getLive(i - 1, m);
        return (live + m -1 ) % i + 1;
    }
    /**
     * 时间复杂度：O(n * m)
     * @param size
     * @param m
     */
    private static Node josephProblem(Node head, int size, int m){
        if(m > size || m < 1){
            return head;
        }
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
        return curr;
    }

    private static Node initCyclicLinkedList(int initNum) {
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
