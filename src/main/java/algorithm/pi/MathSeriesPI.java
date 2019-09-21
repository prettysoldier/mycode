package algorithm.pi;

/**
 * ∑1/(n^2)=(π^2)/6
 * @author heshuaijun
 * @date 2019/9/21 14:49
 */
public class MathSeriesPI {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(math_Pi(1000));//改变参数值
    }
    public static double math_Pi(int n) {
        int numInCircle = 0;
        double sum = 0;
        double pi;
        for(int i=1;i <= n; i++){
            sum += 1.0/(i*i);
        }
        pi = Math.sqrt(sum * 6);
        return pi;
    }
}

