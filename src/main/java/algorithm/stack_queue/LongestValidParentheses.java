package algorithm.stack_queue;

import java.util.Stack;

/**
 * @author hsj
 * @create 2019/12/23
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")(()(()("));
    }
    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        int accumulatedLen = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else {
                if(stack.isEmpty()){
                    accumulatedLen = 0;
                }else{
                    int matchedPos = stack.pop();
                    int matchedLen;
                    if (stack.isEmpty()) {
                        //如果栈为空，没有左括号可以匹配
                        matchedLen = i - matchedPos + 1;
                        accumulatedLen += matchedLen;
                        //更新当前匹配括号序列长度
                        matchedLen = accumulatedLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }
                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }

        return maxLen;
    }
}
