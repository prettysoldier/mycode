package algorithm.stack_queue.getmin;

import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/9/21 22:44
 */
public class GetMinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public int pop() throws Exception {
        if(stack.size() > 0){
            if(stack.peek() == minStack.peek()){
                this.minStack.pop();
            }
            return this.stack.pop();
        }
        if(minStack.size() > 0){
            return minStack.pop();
        }
        throw new Exception("stack is empty");
    }

    public void push(Integer i){
        if(minStack.isEmpty()){
            minStack.push(i);
            return;
        }
        if(i <= minStack.peek()){
            minStack.push(i);
        }
        stack.push(i);
    }

    public int getMin() throws Exception {
        if(minStack.isEmpty()){
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }

    public static void main (String[] args) throws Exception {

        GetMinStack stack = new GetMinStack();
        stack.push(2);
        stack.push(4);
        stack.push(1);
        System.out.println(stack.getMin());
    }
}
