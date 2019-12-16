package algorithm;

/**
 * @author hsj
 * @create 2019/12/16
 */
public class UglyNumber {

    public static void main(String[] args) {
        System.out.println(new UglyNumber().nthUglyNumber(9));
    }

    public int nthUglyNumber(int n) {
        // write your code here
        int c = 0;
        int i = 0;
        while(c < n){
            i++;
            if(isUglyNumber(i)){
                c++;
            }
        }
        return i;
    }

    private boolean isUglyNumber(int n){
        while(n % 2 == 0){
            n /= 2;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        while(n % 5 == 0){
            n /= 5;
        }
        return n == 1;
    }
}
