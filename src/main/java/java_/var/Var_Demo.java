package java_.var;

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
    }
}
