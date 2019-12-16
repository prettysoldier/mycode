package algorithm.maze;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * 牛郎织女迷宫
 * @author heshuaijun
 * @date 2019/12/15 23:28
 */
public class CowherdWeaverMaze {

    public static void main (String[] args) {
        String[] maze = new String[]{"....*",".....",".....","*S**.","T**.*"};
        System.out.println(findHer(maze));
    }
    public static  boolean findHer(String[] maze) {
        // 找出牛郎和织女的位置
        int sx = 0, sy = 0;
        int tx = 0, ty = 0;
        for(int y = 0 ; y < maze.length; y++){
            for(int x = 0; x < maze[y].length(); x++){
                if(maze[y].charAt(x) == 'S'){
                    sx = x;
                    sy = y;
                }
                if(maze[y].charAt(x) == 'T'){
                    tx = x;
                    ty = y;
                }
            }
        }
        // 岔路口入栈
        Stack<Point> stack = new Stack<>();
        Set<Point> passed = new HashSet<>();
        Point start = new Point(sx, sy, 0);
        stack.push(start);
        passed.add(start);

        int d = 0;
        while(!stack.isEmpty()) {
            Point curr = stack.pop();
            if(isHer(curr.x, curr.y, tx, ty)){
                return true;
            }
            d = direction(maze, curr.x, curr.y, curr.prevDirect);
            if (d >= 0) {
                Point p;
                if ((d & RIGHT) == RIGHT) {
                    p = new Point(curr.x + 1, curr.y, LEFT);
                    if(!passed.contains(p)){
                        stack.push(p);
                        passed.add(p);
                    }
                }
                if ((d & LEFT) == LEFT) {
                    p = new Point(curr.x - 1, curr.y, RIGHT);
                    if(!passed.contains(p)){
                        stack.push(p);
                        passed.add(p);
                    }
                }
                if ((d & DOWN) == DOWN) {
                    p = new Point(curr.x, curr.y+1, UP);
                    if(!passed.contains(p)){
                        stack.push(p);
                        passed.add(p);
                    }
                }
                if ((d & UP) == UP) {
                    p = new Point(curr.x, curr.y-1, DOWN);
                    if(!passed.contains(p)){
                        stack.push(p);
                        passed.add(p);
                    }
                }

            }

        }
        return false;
    }

    private static final int RIGHT = 1;
    private static final int DOWN = 1 << 1;
    private static final int LEFT = 1 << 2;
    private static final int UP = 1 << 3;

    private static int direction(String[] maze, int x, int y, int prevDirect){
        int ret = 0;
        int weight = maze[0].length();
        int height = maze.length;
        if(x+1 < weight && prevDirect != RIGHT && (maze[y].charAt(x+1) == 'T' || maze[y].charAt(x+1) == '.')){
            ret |= RIGHT;
        }
        if(x-1 >= 0 && prevDirect != LEFT && (maze[y].charAt(x-1) == 'T' || maze[y].charAt(x-1) == '.')){
            ret |= LEFT;
        }
        if(y+1 < height && prevDirect != DOWN && (maze[y+1].charAt(x) == 'T' || maze[y+1].charAt(x) == '.')){
            ret |= DOWN;
        }
        if(y-1 >= 0 && prevDirect != UP && (maze[y-1].charAt(x) == 'T' || maze[y-1].charAt(x) == '.')){
            ret |= UP;
        }
        return ret;
    }
    private static int directCount(int direct){
        int count = 0;
        if((direct & RIGHT) == RIGHT){
            count++;
        }
        if((direct & LEFT) == LEFT){
            count++;
        }
        if((direct & UP) == UP){
            count++;
        }
        if((direct & DOWN) == DOWN){
            count++;
        }
        return count;
    }

    private static boolean isHer(int x, int y, int tx, int ty){
        return (x == tx) && (y == ty);
    }


}
class Point{
    int x;
    int y;
    int prevDirect;

    Point (int x, int y, int oldDirect) {
        this.x = x;
        this.y = y;
        this.prevDirect = oldDirect;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode () {
        return Objects.hash(x, y);
    }
}