package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hsj
 * @create 2019/12/16
 */
public class UglyNumber2 {

    public static void main(String[] args) {
        System.out.println(new UglyNumber2().nthUglyNumber(9));
    }

    public int nthUglyNumber(int n) {
        List<Integer> uglys = new ArrayList<Integer>(10);
        uglys.add(1);

        int p2 = 0, p3 = 0, p5 = 0;
        // p2, p3 & p5 share the same queue: uglys

        for (int i = 1; i < n; i++) {
            int lastNumber = uglys.get(i - 1);
            while (uglys.get(p2) * 2 <= lastNumber) {
                p2++;
            }
            while (uglys.get(p3) * 3 <= lastNumber) {
                p3++;
            }
            while (uglys.get(p5) * 5 <= lastNumber) {
                p5++;
            }

            uglys.add(Math.min(
                    Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                    uglys.get(p5) * 5
            ));
        }

        return uglys.get(n - 1);
    }
}
