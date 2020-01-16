package source_code_java.java_.lang.object;

/**
 * final 域，不会被初始化为默认值，需要显示地赋值。
 * @author heshuaijun
 * @date 2019/11/23 23:09
 */
public class FinalTest {

    // 静态常量不初始化会报错！
//    static final int i;

    /**
     * 实例常量必须在构造器（所有）中初始化，否则报错！
     */
    final int i2;

//    private FinalTest () {}

    private FinalTest (int i) {
        this.i2 = i;
    }

    public static void main (String[] args) {

    }
}
