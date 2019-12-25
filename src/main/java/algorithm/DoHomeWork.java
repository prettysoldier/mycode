package algorithm;

/**
 * @author hsj
 * @create 2019/12/25
 */
public class DoHomeWork {
    public long doingHomework(int[] cost, int[] val) {
        // Write your code here.
        long data = 0;
        long tmp;
        for(int t : val){
            tmp = 0;
            for(int c : cost){
                if(tmp + c > t){
                    break;
                }
                tmp += c;
            }
            data += tmp;
        }
        return data;
    }

    public static void main(String[] args) {

        long time = new  DoHomeWork().doingHomework(new int[]{1,2,3,5}, new int[]{6,10,4});
        System.out.println(time);
    }
}
