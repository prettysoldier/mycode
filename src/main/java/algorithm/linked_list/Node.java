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
}
