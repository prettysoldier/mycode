package algorithm.stack_queue.queue_by_stack;

import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/9/21 23:41
 */
public class QueueByStack {

    private Stack<Integer> left = new Stack<>();
    private Stack<Integer> right = new Stack<>();

    public void add(Integer i){
        left.push(i);
        if(right.isEmpty()){
            while(!left.isEmpty()){
                right.push(left.pop());
            }
        }
    }

    public int poll() throws Exception{
        if(!right.isEmpty()){
            return right.pop();
        }
        if(left.isEmpty()){
            throw new Exception("queue is empty");
        }
        while(!left.isEmpty()){
            right.push(left.pop());
        }
        return right.pop();
    }

    public int peek() throws Exception{
        if(!right.isEmpty()){
            return right.peek();
        }
        if(left.isEmpty()){
            throw new Exception("queue is empty");
        }
        while(!left.isEmpty()){
            right.push(left.pop());
        }
        return right.peek();
    }

    public static void main (String[] args) throws Exception{

        QueueByStack queue = new QueueByStack();
        queue.add(1);
        queue.add(4);
        queue.add(2);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
