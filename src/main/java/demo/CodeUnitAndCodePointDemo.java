package demo;

/**
 * @author hsj
 * @create 2019/12/13
 */
public class CodeUnitAndCodePointDemo {
    public static void main(String[] args) {
        String s = "𤋮";
        System.out.println(s.getBytes().length);
        System.out.println(s.codePoints().count());
        System.out.println(s.length());
        System.out.println(s.codePointCount(0, s.length()));
    }
}
