package algorithm;

/**
 * @author Shuaijun He
 */
public class Rebound {
    public static void main(String[] args) {
        double s = 0;
        double h = 100;
        for (int i = 1; i <= 10; i++) {
            s += h;
            h = h / 2;
            s += h;
        }
        System.out.println("经过路程：" + (s - h));
        System.out.println("反弹高度：" + h);
    }
}