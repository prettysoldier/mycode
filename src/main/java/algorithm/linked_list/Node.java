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
}
