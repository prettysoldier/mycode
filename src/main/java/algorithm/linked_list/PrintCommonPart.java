package algorithm.linked_list;

import java.util.*;

/**
 * 题目：给定两个有序链表的头指针head1和head2，打印两个链表的公共部分
 * 难度：1
 *
 * @author shuaijunhe
 * @create 2019/10/29
 * @description
 */
public class PrintCommonPart {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(3, 4, 5, 6, 7));
        Integer head1 = list1.poll();
        Integer head2 = list2.poll();
        while(head1 != null && head2 != null){
            if(head1 < head2){
                head1 = list1.poll();
            }else if (head2 < head1){
                head2 = list2.poll();
            }else{
                System.out.println(head1);
                head1 = list1.poll();
                head2 = list2.poll();
            }
        }
    }
}
