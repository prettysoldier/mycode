package algorithm.stack_queue.hanoi_tower;

import java.util.Stack;

/**
 * 算法第一步，明确输入和输出。
 * 输入：N
 * 输出：打印过程和总步数
 * 
 * @author heshuaijun
 * @date 2019/9/21 20:26
 */
public class HanoiTowerWithStack {


    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String MID = "mid";
    private static int NUM = 5;

    private static final Stack<Integer> LEFT_STACK = new Stack<>();
    private static final Stack<Integer> RIGHT_STACK = new Stack<>();
    private static final Stack<Integer> MID_STACK = new Stack<>();

    public static void main (String[] args) {

        int num = NUM;
        if(num <= 0){
            return;
        }
        LEFT_STACK.push(Integer.MAX_VALUE);
        RIGHT_STACK.push(Integer.MAX_VALUE);
        MID_STACK.push(Integer.MAX_VALUE);

        while (num > 0){
            LEFT_STACK.push(num);
            num--;
        }
        Action[] action = {Action.NO};
        int step = 0;
        while(RIGHT_STACK.size() < NUM + 1){
            step += move(action, Action.L_M, Action.M_L, MID_STACK, LEFT_STACK, MID, LEFT);
            step += move(action, Action.M_L, Action.L_M, LEFT_STACK, MID_STACK, LEFT, MID);
            step += move(action, Action.R_M, Action.M_R, MID_STACK, RIGHT_STACK, MID, RIGHT);
            step += move(action, Action.M_R, Action.R_M, RIGHT_STACK, MID_STACK, RIGHT, MID);
        }
        System.out.println("总共需要" + step + "步");

    }

    private static int move(Action[] action, Action prevAction, Action nowAction,
                            Stack<Integer> fromStack, Stack<Integer> toStack, String from, String to){
        if(action[0] != prevAction && fromStack.peek() < toStack.peek()){
            toStack.push(fromStack.pop());
            System.out.println("Move " + toStack.peek() + " from " + from + " to " + to);
            action[0] = nowAction;
            return 1;
        }
        return 0;
    }
    private enum Action{
        NO, L_M, M_L, M_R, R_M;
    }

}
