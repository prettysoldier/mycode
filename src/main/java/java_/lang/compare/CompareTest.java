package java_.lang.compare;

import java.util.*;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 17:14
 **/
public class CompareTest {
    public static void main(String[] args) {
        List<A> list = new ArrayList<>(10);
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            list.add(new A(random.nextInt(10)));
        }
        list.forEach(e->{
            System.out.print(e.getA() + ", ");
        });
        System.out.println();
        arraysSort(list);
//        arraysSort2(list);
    }

    private static void arraysSort(List<A> list) {
        A[] arr = new A[list.size()];
        // Arrays的sort方法，的前提是 数组元素实现了Comparable接口
        Arrays.sort(list.toArray(arr));
        Arrays.stream(arr).forEach(e-> System.out.print(e.getA() + ", "));
    }

    private static void arraysSort2(List<A> list) {
        A[] arr = new A[list.size()];
        // Arrays的sort方法，的前提是 数组元素实现了Comparable接口
        Arrays.sort(list.toArray(arr), new MyComparator());
        Arrays.stream(arr).forEach(e-> System.out.print(e.getA() + ", "));
    }
}

class A implements Comparable<A>{
    private int a;

    public A(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    @Override
    public int compareTo(A o) {
        if(this.a != o.getA()){
            // 逆序
            return this.a > o.getA() ? -1: 2;
        }
        return 0;
    }
}

class MyComparator implements Comparator<A>{
    @Override
    public int compare(A o1, A o2) {
        if(o1.getA() != o2.getA()){
            return o1.getA() > o2.getA() ? 1 : -1;
        }
        return 0;
    }
}