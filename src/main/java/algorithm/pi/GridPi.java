package algorithm.pi;

/**
 * 将图片分为n*n个小方形，统计落在圆内的个数占所有方形的比列。
 * @author heshuaijun
 * @date 2019/9/21 14:55
 */
public class GridPi {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(grid_Pi(100));//改变参数值
    }
    public static double grid_Pi(int n) {
        int i;
        double sum=0;
        for(i = 0;i < n; i++) {
            // 使用的勾股定义，求每条横线的长度
            // 求比例的时候，用四分之一园来计算也是可以的，因为比值不变。
            sum += (int) Math.sqrt(n * (double) n - i * (double) i);
        }
        return (4.0 * sum)/n/n;
    }
}
