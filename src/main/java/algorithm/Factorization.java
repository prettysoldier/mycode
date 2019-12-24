package algorithm;

import java.util.Scanner;

/**
 * @author Shuaijun He
 */
public class Factorization {

    /**
     * 因式分解
     * @param n
     */
    public static void factorize(int n) {
        if(n <= 1){
            return;
        }

        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i);
                if (n != i) {
                    System.out.print("*");
                }
                factorize(n / i);
            }
        }
        System.exit(0);
    }

    public static void factorize2(int n) {
        if(n <= 1){
            return;
        }
        int m = n;
        int i =2, j = 2;
        while (i <= n / 2 + 1) {
            if(m < 2){
                break;
            }
            if (m % j == 0) {
                System.out.print(j);
                if (n != i) {
                    System.out.print("*");
                }
                m /= j;
                j = 2;
                continue;
            }
            j++;
            i++;
        }
    }

    public static void main(String[] args) {

        while(true){

            Scanner in = new Scanner(System.in);
            System.out.print("请输入N的值：");
            int n = in.nextInt();
            System.out.print("分解质因数：" + n + "=");
//            factorize(n);
            factorize2(n);
            System.out.println();
        }
    }
}
