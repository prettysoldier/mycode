package test.dispatch;

/**
 * 这道题 考验对重载的理解程度
 * 静态分派，用于方法重载，发生在编译阶段
 *
 * @author Shuaijun He
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello, guy");
    }

    public void sayHello(Man guy) {
        System.out.println("hello, gentleman");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello, lady");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }

}
