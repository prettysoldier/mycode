package algorithm.linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针节点的链表
 *
 * @author heshuaijun
 * @date 2019/10/31 22:06
 */
public class CopyRandomPointerLinkedList {

    public static void main (String[] args) {

        RandomPointerNode head = RandomPointerNode.init();
        RandomPointerNode newHead = copyWithMap(head);
//        RandomPointerNode newHead = copy(head);

        System.out.println(isEqual(head, newHead));
    }

    /**
     * 进阶，不需要额外的map
     * @param head
     * @return
     */
    private static RandomPointerNode copy(RandomPointerNode head){

        // 每个节点后面后插入一个复制节点，串成一长链表
        RandomPointerNode curr = head;
        RandomPointerNode next;
        while(curr != null){
            next = curr.next;
            RandomPointerNode node = new RandomPointerNode(curr.value);
            curr.next = node;
            node.next = next;

            curr = next;
        }
        // 设置新链表的rand
        curr = head;
        while(curr != null){
            if(curr.rand != null){
                curr.next.rand = curr.rand.next;
            }
            curr = curr.next.next;
        }
        // 将长链表拆开
        curr = head;
        RandomPointerNode newHead = head.next;
        while(curr.next.next != null){
            next = curr.next;
            curr.next = next.next;
            next.next = curr.next.next;

            curr = curr.next;
        }
        // 最后一对新旧节点要拆开！
        curr.next = null;
        return newHead;
    }

    private static RandomPointerNode copyWithMap(RandomPointerNode head){

        Map<RandomPointerNode, RandomPointerNode> map = new HashMap<>();
        RandomPointerNode prev = null;
        RandomPointerNode curr = head;
        while(curr != null){
            RandomPointerNode node = new RandomPointerNode(curr.value);
            map.put(curr, node);
            if(prev != null){
                prev.next = node;
            }
            prev = node;
            curr = curr.next;
        }
        curr = head;
        RandomPointerNode currNew;
        while(curr != null){
            currNew = map.get(curr);
            if(curr.rand != null){
                currNew.rand = map.get(curr.rand);
            }else{
                currNew.rand = null;
            }
            curr = curr.next;
        }
        return map.get(head);
    }

    static class RandomPointerNode{
        public int value;
        public RandomPointerNode next;
        public RandomPointerNode rand;

        public RandomPointerNode (int value) {
            this.value = value;
        }

        public static RandomPointerNode init(){
            RandomPointerNode node1 = new RandomPointerNode(1);
            RandomPointerNode node2 = new RandomPointerNode(2);
            RandomPointerNode node3 = new RandomPointerNode(3);
            node1.next = node2;
            node2 .next = node3;
            node1.rand = node3;
            node3.rand = node1;
            return node1;
        }
    }

    private static boolean isEqual(RandomPointerNode node1, RandomPointerNode node2){
        // 相等，或都是null
        if(node1 == node2){
            return false;
        }
        // node2必然不为null
        if(node1 == null || node2 == null){
            return false;
        }
        while(node1 != null){
            if(node1.value != node2.value
                    || (node1.rand != null && node2.rand != null && node1.rand.value != node2.rand.value)){
                return false;
            }
            if( (node1.rand == null && node2.rand != null) || (node1.rand != null && node2.rand == null)){
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
            if((node1 == null && node2 != null) || (node1 != null && node2 == null)){
                return false;
            }
        }
        return true;
    }
}
