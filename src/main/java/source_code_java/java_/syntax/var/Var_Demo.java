package source_code_java.java_.syntax.var;

/**
 * 在java中有变量的声明和赋值；在C/C++中区分变量的定义和声明，而在java中不区分。
 * @author hsj
 * @create 2019/11/20
 */
public class Var_Demo {
    int i;
    {
        System.out.println(i);

        int i2;
        // 在块中变量不初始化就会报错！！
//        System.out.println(i2);
    }

    public static void main(String[] args) {
        new Var_Demo();
        // 变量命名问题
        // java中的“字母”和“数字”的范围很大。汉字、希腊字母都算是字母，罗马数字等都可以算是数字。
        // 不能用在变量名中的是：- + 版权符号
        // 不能以阿拉伯数字开头！
        char ⅦⅢ齉__8s好;
        int 是dfII;
        int $_IIasdf;
        System.out.println(Character.isJavaIdentifierPart('齉'));
        System.out.println(Character.isJavaIdentifierStart('9'));

    }
}
