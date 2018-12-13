package java_.compare;

import java.util.*;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 17:14
 **/
public class CompareTest {
    public static void main(String[] args) {
        List<ComparatorA> list = new ArrayList<>(10);
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            list.add(new ComparatorA(random.nextInt(10)));
        }
        list.forEach(e->{
            System.out.print(e.getA() + ", ");
        });
        System.out.println();
//        arraysSort(list);
        arraysSort2(list);
    }

    private static void arraysSort(List<ComparatorA> list) {
        ComparatorA[] arr = new ComparatorA[list.size()];
        // Arrays的sort方法，的前提是 数组元素实现了Comparable接口
        Arrays.sort(list.toArray(arr));
        Arrays.stream(arr).forEach(e->{
            System.out.println(e.getA());
        });
    }

    private static void arraysSort2(List<ComparatorA> list) {
        ComparatorA[] arr = new ComparatorA[list.size()];
        // Arrays的sort方法，的前提是 数组元素实现了Comparable接口
        Arrays.sort(list.toArray(arr), new ComparatorB());
        Arrays.stream(arr).forEach(e->{
            System.out.println(e.getA());
        });
    }
}

class ComparatorA implements Comparable<ComparatorA>{
    private int a;

    public ComparatorA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    @Override
    public int compareTo(ComparatorA o) {
        if(this.a != o.getA()){
            return this.a > o.getA() ? -1: 2;
        }
        return 0;
    }
}

class ComparatorB implements Comparator<ComparatorA>{
    @Override
    public int compare(ComparatorA o1, ComparatorA o2) {
        if(o1.getA() != o2.getA()){
            return o1.getA() > o2.getA() ? 1 : -1;
        }
        return 0;
    }
}