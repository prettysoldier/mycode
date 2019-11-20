package java_.lang.char_;

/**
 * 1. java内部其实是使用的UTF-16的编码，所以是支持大部分非生僻汉字的。
 *
 * @author hsj
 * @create 2019/11/20
 */
public class CharDemo {
    int si;

    public static void main(String[] args) {

        char c = '\b';
        System.out.println(c);
        int i = '\b';
        System.out.println(i);
        int i2 = '齉';
        System.out.println(i2);

        char c2 = '"';
        System.out.println(c2);
        char c3 = '\"';
        System.out.println(c3);
        System.out.println(c2 == c3);
        // java中的“字母”和“数字”的范围很大。汉字、希腊字母都算是字母，罗马数字等都可以算是数字。
        // 不能用在变量名中的是：- + 版权符号
        // 不能以阿拉伯数字开头！
        char ⅦⅢ齉__8s好;
        System.out.println(Character.isJavaIdentifierPart('齉'));
        System.out.println(Character.isJavaIdentifierStart('9'));

        System.out.println(new CharDemo().si);
    }
}
