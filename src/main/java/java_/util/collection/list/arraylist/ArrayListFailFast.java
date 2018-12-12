
package java_.util.collection.list.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuaijun He
 */
@SuppressWarnings("ALL")
public class ArrayListFailFast {

    public static void main(String[] args) throws InterruptedException {
//        addWithForLoop();
//        removeWithForLoop();
        addWithForEach();
//        removeWithForEach();
    }
    
    /**
     * 可以在（for (int i = 0; i < list.size(); i++)）循环中增加,但是可能导致循环无法结束！！因为size()的值一直在变大！
     * @author Administrator
     * @throws InterruptedException
     */
    private static void addWithForLoop() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        // 可以在循环时增加
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
            System.out.println(list.size() + ":" + i);
            Thread.sleep(1000);
        }
        System.out.println(list.toArray());
    }

    /**
     * 可以在（for (int i = 0; i < list.size(); i++)）循环时remove，但是可能导致循环提前结束！！
     * @author Administrator
     * @throws InterruptedException
     */
    private static void removeWithForLoop() throws InterruptedException {
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(1);
        // 可以在循环时remove
        for (int i = 0; i < list2.size(); i++) {
            list2.remove(i);
            System.out.println(list2.size() + ":" + i);
            Thread.sleep(1000);
        }
    }

    
    
    /** 
     * 不可以在for each 循环时增加
     * @author Administrator
     * @throws InterruptedException
     */
    private static void addWithForEach() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
       
        int j=0;
        for (Integer i: list) {
            list.add(++j);// ConcurrentModificationException
            System.out.println(list.size() + ":" + i);
            Thread.sleep(1000);
        }
    }
    /**
     * 在for each 过程中remove 竟然不报错! 是由于巧合！
     * 会报错ConcurrentModificationException
     * @author Administrator
     * @throws InterruptedException
     */
    private static void removeWithForEach() throws InterruptedException {
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(1);
        list2.add(2);
        // 可以在循环时remove
        for (Integer i: list2) {
            list2.remove(i);
            System.out.println(list2.size() + ":" + i);
            Thread.sleep(1000);
        }
    }
}
