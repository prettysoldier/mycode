package algorithm.stack_queue.hanoi_tower;

/**
 * 算法第一步，明确输入和输出。
 * 输入：N
 * 输出：打印过程和总步数
 * 
 * @author heshuaijun
 * @date 2019/9/21 20:26
 */
public class HanoiTowerWithRecursion {

    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String MID = "mid";
    private static final int NUM = 5;

    public static void main (String[] args) {

        int count = process(NUM, LEFT, RIGHT);
        System.out.println("总共需要" + count + "步");
    }


    private static int process(int num, String from, String to){

        if(num == 1){
            // 如果起点和终点有一个是中间，则只需要1步
            if(from.equals(MID) || to.equals(MID)){
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            }else{
                // 如果起点和终点不包含中间，则需要两步
                System.out.println("Move 1 from " + from + " to " + MID);
                System.out.println("Move 1 from " + MID + " to " + to);
                return 2;
            }
        }
        if(from.equals(MID) || to.equals(MID)){
            // 需要3步
            String another = (from.equals(LEFT) || to.equals(LEFT)) ? RIGHT : LEFT;
            int part1 = process(num - 1, from, another);
            int part2 = 2;
            System.out.println("Move " + num + " from " + from + " to " + MID);
            System.out.println("Move " + num + " from " + MID + " to " + another);
            int part3 = process(num - 1, another, to);
            return part1 + part2 + part3;
        }else{
            // 需要5步
            int part1 = process(num - 1, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + MID);
            int part3 = process(num - 1, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + MID + " to " + to);
            int part5 = process(num - 1, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }


}
