
package main.test.pattern.single;

/**
 * @author Shuaijun He
 */
public class Singleton4StaticInnerClass {

    private static class SingletonHolder {
        private static final Singleton4StaticInnerClass INSTANCE = new Singleton4StaticInnerClass();
    }

    private Singleton4StaticInnerClass() {
    }

    /**
     * 使用final方法的原因有二：
     * 第一、把方法锁定，防止任何继承类修改它的意义和实现。
     * 第二、高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。
     *
     * @return
     */
    public static final Singleton4StaticInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Singleton4StaticInnerClass getInstance1() {
        return SingletonHolder.INSTANCE;
    }
}
