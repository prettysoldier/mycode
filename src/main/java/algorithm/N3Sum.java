package algorithm;

import java.util.*;

/**
 * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 * 在三元组(a, b, c)，要求a <= b <= c。
 * <p>
 * 结果不能包含重复的三元组。
 *
 * @author hsj
 * @create 2019/12/16
 */
public class N3Sum {

    public static void main(String[] args) {
        List<List<Integer>> data = new N3Sum().threeSum(new int[]{-2, -3, 5, -1, -4, 5, -11, 7, 1, 2, 3, 4, -7, -1, -2, -3, -4, -5});
        System.out.println(data);
    }

    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        Set<Tuple> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {

                        int a = numbers[i];
                        int b = numbers[j];
                        int c = numbers[k];
                        if (a > b) {
                            int tmp = a;
                            a = b;
                            b = tmp;
                        }
                        if (b > c) {
                            int tmp = b;
                            b = c;
                            c = tmp;
                        }
                        if (a > b) {
                            int tmp = a;
                            a = b;
                            b = tmp;
                        }
                        set.add(new Tuple(a, b, c));
                    }
                }
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> e;
        for (Tuple t : set) {
            e = new ArrayList<>(3);
            e.add(t.a);
            e.add(t.b);
            e.add(t.c);
            ret.add(e);
        }
        return ret;
    }

}

class Tuple {
    int a, b, c;

    Tuple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Tuple)){
            return false;
        }
        if(this == o){
            return true;
        }
        Tuple tuple = (Tuple) o;
        return a == tuple.a &&
                b == tuple.b && c == tuple.c;
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}