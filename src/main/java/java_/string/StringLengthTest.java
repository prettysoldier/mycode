package java_.string;

/**
 * String 其实是 char 数组： private final char value[];
 *
 * @author hsj
 * @create 2019/11/19
 */
public class StringLengthTest {
    public static void main(String[] args) {
        String s = "稍等";
        // 输出：2
        System.out.println(s.length());
    }
}
