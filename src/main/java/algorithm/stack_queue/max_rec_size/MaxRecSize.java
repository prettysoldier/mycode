package algorithm.stack_queue.max_rec_size;

import java.util.Stack;

/**
 * 最大矩阵的大小
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class MaxRecSize {

    private static int getMaxRecSize(int[][] map){

        if(map == null || map.length == 0 || map[0].length == 1){
            return 0;
        }
        int max = 0;
        int[] height = new int[map[0].length];

        for(int i = 0; i < map.length; i++){

            for(int j = 0; j < map[0].length; j++){
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            max = Math.max(getMaxRecFromBootom(height), max);
        }
        return max;
    }

    private static int getMaxRecFromBootom(int[] height){

        if(height == null || height.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i < height.length; i++){

            while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
                int pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - left -1) * height[pop]);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, (height.length -left - 1) * height[pop]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
//        int[][] input = new int[][]{{0,1,0,0,1,1}, {0,1,1,1,0,0}, {0,1,1,1,1,0}};
//
//        System.out.println(getMaxRecSize(input));

    }
}
