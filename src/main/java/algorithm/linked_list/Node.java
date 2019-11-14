package algorithm.linked_list;

/**
 * @author heshuaijun
 * @date 2019/10/29 22:13
 */
public class Node {
    private int value;
    private Node next;

    public Node (int value) {
        this.value = value;
    }

    public int getValue () {
        return value;
    }

    public Node setValue (int value) {
        this.value = value;
        return this;
    }

    public Node getNext () {
        return next;
    }

    public Node setNext (Node next) {
        this.next = next;
        return this;
    }

    public static void printLinkedList (Node newHead) {
        while(newHead != null){
            System.out.print(newHead.getValue() + " ");
            newHead = newHead.getNext();
        }
        System.out.println();
    }

    public static void printCyclicLinkedList (Node newHead, int size) {
        int i = 0;
        while(i < size && newHead != null){
            i++;
            System.out.print(newHead.getValue() + " ");
            newHead = newHead.getNext();
        }
        System.out.println();
    }

    public static Node initLinkedList (int size) {
        Node head = new Node(1);
        Node curr = head;
        for(int i = 2; i <= size; i++){
            Node node = new Node(i);
            curr.setNext(node);
            curr = node;
        }
        return head;
    }

    public static Node initLinkedList (int... values) {
        if(values.length == 0){
            return null;
        }
        if(values.length == 1){
            return new Node(values[0]);
        }
        Node prev = null;
        Node head = null;
        for (int e : values) {
            Node n = new Node(e);
            if(prev != null){
                prev.setNext(n);
            }else{
                head = n;
            }
            prev = n;
        }
        return head;
    }

    /**
     * 初始化环形链表
     * @param values
     * @return
     */
    public static Node initCyclicLinkedList (int... values) {
        if(values.length == 0){
            return null;
        }
        if(values.length == 1){
            Node node = new Node(values[0]);
            node.setNext(node);
            return node;
        }
        Node prev = null;
        Node head = null;
        for (int e : values) {
            Node n = new Node(e);
            if(prev != null){
                prev.setNext(n);
            }else{
                head = n;
            }
            prev = n;
        }
        prev.setNext(head);
        return head;
    }
}
