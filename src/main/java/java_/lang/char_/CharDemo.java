package java_.lang.char_;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 1. java内部其实是使用的UTF-16的编码，所以是支持大部分非生僻汉字的。
 *
 * @author hsj
 * @create 2019/11/20
 */
public class CharDemo {
    int si;

    public static void main(String[] args) {

//        baseTest();


        charLength();
    }

    private static void baseTest () {
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
    }

    private static void charLength(){
        Character  a ='a';
        Character  b ='齉';
        Integer c = 70000;
        System.out.println(String.valueOf(a).getBytes(StandardCharsets.UTF_16).length);
        System.out.println(Arrays.toString(String.valueOf(a).getBytes(StandardCharsets.UTF_16)));
        System.out.println(new String(String.valueOf(a).getBytes(StandardCharsets.UTF_16)));

        System.out.println(b.toString().getBytes(StandardCharsets.UTF_16).length);
        System.out.println(Arrays.toString(String.valueOf(b).getBytes(StandardCharsets.UTF_16)));
        System.out.println(new String(String.valueOf(b).getBytes(StandardCharsets.UTF_16)));
        System.out.println(new String(String.valueOf(b).getBytes(StandardCharsets.UTF_8)));

        System.out.println(c.toString().getBytes(StandardCharsets.UTF_16).length);
        System.out.println(Arrays.toString(String.valueOf(c).getBytes(StandardCharsets.UTF_16)));
        System.out.println(new String(String.valueOf(c).getBytes(StandardCharsets.UTF_16)));

        // 比如下面这个字就无法放在Character里！
//        Character d = '𤋮';
        // 拷贝到String,自动变成两个码元
        String s = "\uD850\uDEEE";
        System.out.println(s);
        String s2 = "𤋮";
        System.out.println(s2);
        System.out.println(s2.getBytes().length);
        System.out.println(s2.codePoints().count());
    }
}
