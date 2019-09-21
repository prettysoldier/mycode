package algorithm.pi;

/**
 * 蒙特卡洛法（概率统计法）
 * @author heshuaijun
 * @date 2019/9/21 14:46
 */
public class MonteCarloPi {
    public static void main (String[] args) {
        // TODO Auto-generated method stub
        System.out.println(rand_pi(100000000));  //改变参数值
    }

    public static double rand_pi (int n) {
        int numInCircle = 0;
        double x, y;
        double pi;
        for (int i = 0; i < n; i++) {
            x = Math.random();
            y = Math.random();
            if (x * x + y * y < 1)
                numInCircle++;
        }
        pi = (4.0 * numInCircle) / n;
        return pi;
    }
}
