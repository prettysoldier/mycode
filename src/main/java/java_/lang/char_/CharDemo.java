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

        int i = 2;
        double l = i;
        byte b = 1;
        short s = b;
//        short s1 = s + b;
//        baseTest();
//        charLength();
        codePointTest();
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
        System.out.println(s.length());
        System.out.println(s.codePointCount(0, s.length()));
        // 这里是拿不到实际的字符的，因为只能拿到一半。
        System.out.println(s.charAt(0));
        // 要想拿到某个实际字符，应该这样：
        int index = s.offsetByCodePoints(0, 0);
        System.out.println(s.codePointAt(index));
        char[] sTOChars = Character.toChars(s.codePointAt(index));
        System.out.println(new String(sTOChars));

    }

    private static void codePointTest(){
        char[] c = Character.toChars(Integer.parseInt("1D306", 16));//1D306是一个辅助平面字符
        System.out.println(Character.codePointAt(c, 0));//输出119558，这个是1D306对应的10进制值
        System.out.println(Character.codePointAt(c, 1));//输出57094，这个是c[1]对应字符的10进制值
        System.out.println(new String(c).codePointAt(0));//输出119558，这个是1D306对应的10进制值
        System.out.println(new String(c).codePointAt(1));//输出57094，这个是c[1]对应字符的10进制值
        String str = "a" + new String(c);
        System.out.println(str.length());//3
        System.out.println(str.codePointCount(0, str.length()));//3

        System.out.println(String.valueOf(c));
    }
}
