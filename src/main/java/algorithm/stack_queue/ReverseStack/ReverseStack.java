package algorithm.stack_queue.ReverseStack;

import java.util.Stack;

/**
 * 使用递归逆序一个栈，不能用其他数据结构
 * @author heshuaijun
 * @date 2019/9/22 0:05
 */
public class ReverseStack {

    private static int getAndMoveBottomItem(Stack<Integer> stack){
        if(stack.size() == 1) {
            return stack.pop();
        }
        int result = stack.pop();
        int bottom = getAndMoveBottomItem(stack);
        stack.push(result);
        return bottom;
    }

    private static void reverse(Stack<Integer> stack){

        if(stack.size() <= 1){
            return;
        }

        int bottom = getAndMoveBottomItem(stack);
        reverse(stack);
        stack.push(bottom);
    }

    public static void main (String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        reverse(stack);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
