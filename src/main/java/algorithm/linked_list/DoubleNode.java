package algorithm.linked_list;

/**
 * @author heshuaijun
 * @date 2019/10/30 0:14
 */
public class DoubleNode {
    private int value;
    private DoubleNode prev;
    private DoubleNode next;

    public DoubleNode (int value) {
        this.value = value;
    }

    public int getValue () {
        return value;
    }

    public DoubleNode getPrev () {
        return prev;
    }

    public void setPrev (DoubleNode prev) {
        this.prev = prev;
    }

    public DoubleNode getNext () {
        return next;
    }

    public void setNext (DoubleNode next) {
        this.next = next;
    }

    public static void printLinkedList (DoubleNode newHead) {
        DoubleNode last = null;
        while(newHead != null){
            System.out.print(newHead.getValue() + " ");
            if(newHead.getNext() == null){
                last = newHead;
            }
            newHead = newHead.getNext();
        }
        System.out.println();
        while(last != null){
            System.out.print(last.getValue() + " ");

            last = last.getPrev();
        }
        System.out.println();
        System.out.println("---------");
    }


}
