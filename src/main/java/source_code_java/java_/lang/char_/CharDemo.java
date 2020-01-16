package source_code_java.java_.lang.char_;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 1. java内部其实是使用的UTF-16的编码，所以是支持大部分非生僻汉字的。
 *
 * @author hsj
 * @create 2019/11/20
 */
public class CharDemo {

    public static void main(String[] args) {
//        stringToLong();
//        stringToFloatDouble();
//        baseTest();
//        charLength();
//        codePointTest();
        System.out.println(Arrays.toString(stringToUnicode("a𤋮a𤋮a𤋮")));
        System.out.println(Arrays.toString(stringToUnicode2("a𤋮a𤋮a𤋮")));
        System.out.println(Arrays.toString(stringToUnicode3("a𤋮a𤋮a𤋮")));
        System.out.println(unicodeArrayToString(stringToUnicode("a𤋮a𤋮a𤋮")));
    }

    private static void baseTest () {
        /**
         * 整数型：byte short int long
         * 浮点型：float double
         * 数值型：整数型（4） 浮点型（2）
         * 基本类型：数值型（6） 字符型（1） 布尔型（1）
         */
        /**
         * 转换中的知识点：
         * 数字不能超出当前数字类型的表示范围：byte de = 128 会报错。
         *      byte de=(byte) 128;System.out.println(de); 结果：-128
         * 四则运算时:
         *      所有的byte型、short型和char的值将被提升到int型
         *      如果一个操作数是long型，计算结果就是long型
         *      如果一个操作数是float型，计算结果就是float型
         *      如果一个操作数是double型，计算结果就是double型
         *      如果一个操作数是String型，计算结果就是String型
         */

        //
        // 数值型： float double
        int i = 2;
        double l = i;
        byte b = 1;
        short s = b;
        // byte、short、int 的 + 操作，会返回int类型，所以下面的操作会报错！
//        short s1 = s + b;
//        s = s + 1;
        // 等同于short s2 = (short)(s2 + (short)1); //正确
        s += 1;

        char c = '\b';
        System.out.println("c: " + c);
        int i2 = '\b';
        System.out.println("i2: " + i2);
        // 下面的也会报错，因为这个汉子放不进一个char里
//        int i3 = '𤋮';
//        System.out.println(i3);

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
//        char d = '𤋮';
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
        System.out.println(str);
        System.out.println(str.length());//3
        System.out.println(new String(c).codePointCount(0, 1));//3

        System.out.println(String.valueOf(c));


        Object[] unicodes = str.codePoints().boxed().toArray();

    }

    public static void codePointTest2() {

        char[] chs = Character.toChars(0x242EE);
        System.out.printf("U+10437 高代理字符: %04x%n", (int)chs[0]);
        System.out.printf("U+10437 低代理字符: %04x%n", (int)chs[1]);
        String str = new String(chs);
        System.out.println("代码单元长度: " + str.length());
        System.out.println("代码点数量: " + str.codePointCount(0, str.length()));
        System.out.println(str);

    }

    public static int[] stringToUnicode(String str){

        Object[] unicodes = str.codePoints().boxed().toArray();
        if(unicodes == null || unicodes.length == 0){
            return new int[0];
        }
        int[] ret = new int[unicodes.length];
        for(int i = 0; i < unicodes.length; i++){
            ret[i] = (int)unicodes[i];
        }
        return ret;
    }

    @Deprecated
    public static int[] stringToUnicode2(String str) {
        if (str == null || "".equals(str)) {
            return new int[0];
        }
        int count = str.codePointCount(0, str.length());
        int[] result = new int[count];

        int i = 0;
        int j = 0;
        for(; i< str.length(); ){
            result[j++] = str.codePointAt(i);
            if(i == str.length() - 1){
                i++;
                continue;
            }
            if(str.codePointCount(i, i + 2) > 1){
                i++;
            }else{
                i += 2;
            }
        }
        return result;
    }

    public static int[] stringToUnicode3(String str) {
        if (str == null || "".equals(str)) {
            return new int[0];
        }
        int count = str.codePointCount(0, str.length());
        int[] result = new int[count];
        int index = 0;
        for (int i = 0 ; i < str.length(); ++i) {
            if (Character.isLowSurrogate(str.charAt(i))) {
                continue;
            }
            result[index++] = str.codePointAt(i);
        }

        return result;
    }

    public static String unicodeArrayToString(int[] unicode){


        StringBuilder sb = new StringBuilder();
        Arrays.stream(unicode).forEach(i -> {
            sb.append(String.valueOf(Character.toChars(i)));
        });

        return sb.toString();
    }

    /**
     * String的字符串进行类型转换时，如果字符串中包含long整形的字符大写L或者小写l时，在转换成字符串时，会出现错误
     */
    private static void stringToLong(){
        String str="123L";
        long str1=Long.parseLong(str);
        System.out.println(str1);//结果：错误
    }

    /**
     * String的字符串是float、double浮点型时，字符串可以加f、F、D、d等，在进行基本类型转换时不会出错
     */
    private static void stringToFloatDouble(){
        String f1="12.34F";
        float f2=Float.parseFloat(f1);
        System.out.println(f2);

        String d1="12.34D";
        double d2=Double.parseDouble(d1);
        System.out.println(d2);
    }

}
