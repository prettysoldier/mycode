package test.algorithm.algori50;

import java.util.Scanner;

/**
 * @author Shuaijun He
 */
public class Demo04 {
    public Demo04() {
        super();
    }

    public void fenjie(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i);
                if (n != i) {
                    System.out.print("*");
                }
                this.fenjie(n / i);
            }
        }
        System.exit(0); //退出程序
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 3) + "");
        Scanner in = new Scanner(System.in);
        System.out.println("请输入N的值：");
        int N = in.nextInt();
        System.out.print("分解质因数：" + N + "=");
        new Demo04().fenjie(N);
    }
}
