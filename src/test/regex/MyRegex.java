
package test.regex;

import java.util.regex.Pattern;

/**
 * @author Shuaijun He
 */
public class MyRegex {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^[A-Z_][A-Z_|,]+[A-Z_]$");
        System.out.println("" + pattern.matcher("sdfgA,dfg").matches());
        System.out.println("" + pattern.matcher("ADSDF,").matches());
        System.out.println("" + pattern.matcher(",FFFGF").matches());
        System.out
            .println("``" + pattern.matcher("DFDFG_DFG,D_DFGD").matches());
        System.out.println("" + pattern.matcher("").matches());
        System.out.println("" + pattern.matcher("").matches());
        System.out.println("" + pattern.matcher("").matches());
        System.out.println("" + pattern.matcher("").matches());

        System.out.println("玛依拉•赛依提哈孜".equals("玛依拉·赛依提哈孜"));
        String s = "玛F依拉••赛*&1依提•哈a孜".replaceAll(
            "[^a-zA-Z\u3400-\u4DB5\u4e00-\u9fa5\uE815-\uFA29]+", "").trim();
        String s1 = "玛F依s的风格和蛋糕盒tyu拉·赛依提哈a孜".replaceAll(
            "[^a-zA-Z\u3400-\u4DB5\u4e00-\u9fa5\uE815-\uFA29a-zA-Z]+", "")
            .trim();
        System.out.println(s);
        System.out.println(s1);

        Pattern p = Pattern.compile("^[^0]\\d{10}$");
        System.out.println("" + p.matcher("15166666666").matches());
    }
}
