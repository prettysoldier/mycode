package algorithm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
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
        Set<Point> set = new HashSet<>();
        stack.push(new Point(sx, sy, 0));
        set.add(new Point(sx, sy, 0));
        int d, x = sx, y = sy, prevDirect = 0;
        while(!stack.isEmpty()) {
            if(isHer(x, y, tx, ty)){
                return true;
            }
            d = direction(maze, x, y, prevDirect);
            if(directCount(d) == 0){
                Point next = stack.pop();
                x = next.x;
                y = next.y;
                prevDirect = next.prevDirect;
                d = direction(maze, x, y, prevDirect);
            }
            if (directCount(d) == 1) {
                if ((d & RIGHT) == RIGHT) {
                    ++x;
                    prevDirect = LEFT;
                }
                if ((d & LEFT) == LEFT) {
                    --x;
                    prevDirect = RIGHT;
                }
                if ((d & DOWN) == DOWN) {
                    y++;
                    prevDirect = UP;
                }
                if ((d & UP) == UP) {
                    y--;
                    prevDirect = DOWN;
                }
            }
            if (directCount(d) > 1) {
                if ((d & RIGHT) == RIGHT) {
                    Point next = new Point(++sx, sy, LEFT);
                    if(!set.contains(next)){
                        stack.push(next);
                        set.add(next);
                    }
                }
                if ((d & LEFT) == LEFT) {
                    Point next = new Point(--sx, sy, RIGHT);
                    if(!set.contains(next)){
                        stack.push(next);
                        set.add(next);
                    }
                }
                if ((d & DOWN) == DOWN) {
                    Point next = new Point(sx, ++sy, UP);
                    if(!set.contains(next)){
                        stack.push(next);
                        set.add(next);
                    }
                }
                if ((d & UP) == UP) {
                    Point next = new Point(sx, --sy, DOWN);
                    if(!set.contains(next)){
                        stack.push(next);
                        set.add(next);
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