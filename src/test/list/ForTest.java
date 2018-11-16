
package test.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuaijun He
 */
@SuppressWarnings("ALL")
public class ForTest {

    public static void main(String[] args) throws InterruptedException {
//        addWithForLoop();
//        removeWithForLoop();
//        addWithForEach();
        removeWithForEach();
    }
    
    /**
     * 可以在循环时增加
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
    }

    /**
     * 可以在循环时remove
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
     * 不可以在循环时增加
     * @author Administrator
     * @throws InterruptedException
     */
    private static void addWithForEach() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
       
        int j=0;
        for (Integer i: list) {
            list.add(++j);
            System.out.println(list.size() + ":" + i);
            Thread.sleep(1000);
        }
    }
    /**
     * 在for each 过程中remove 竟然不报错!
     * @author Administrator
     * @throws InterruptedException
     */
    private static void removeWithForEach() throws InterruptedException {
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(1);
        // 可以在循环时remove
        for (Integer i: list2) {
            list2.remove(i);
            System.out.println(list2.size() + ":" + i);
            Thread.sleep(1000);
        }
    }
}
