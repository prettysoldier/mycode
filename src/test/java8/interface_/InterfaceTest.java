
package test.java8.interface_;

import test.java8.interface_.InterfaceTest.IHello;

/**
 * 可见性与其他class相同
 * only public, protected, private, abstract & static are permitted
 * 其中 static 和 abstract 是多余的
 * <br>
 * 接口方法: 都是 public abstract
 * 接口成员变量：都是public static final
 *
 * @author Shuaijun He
 */
public class InterfaceTest {

    interface IHello {
        int a = 2;

        default void sayHello() throws Exception{};

        /**
         * JDK8 以后接口可以有方法体。
         * 默认方法 default :
         * 可以通过实现接口的类实例化的对象来调用，也可以被重写。
         * 是Public的。
         * 静态方法 static ：
         * 只能在本接口中调用，职责上是工具方法。
         * 这是接口向抽象类的靠近。
         * 
         * @author shuaijunhe
         * @return
         */
        default String haha() {
            return "haha";
        }

        /**
         * 对比抽象类
         * 这是接口向抽象类的靠近。
         * 抽象类可做，接口不可做：
         * 抽象类能够定义 非static final 的属性，而接口的属性都是static final的。
         * 抽象类能够定义 非public 方法，而接口的方法都是public的。
         * 接口可做，抽象类不可做：
         * 接口可以多继承(实现)，而抽象类只能单继承。
         * 
         */
        static int add(int a, int b) {
            return a + b;
        }
    }

    interface IHello2 extends IHello {

        void sayHello();
    }

    class B implements IHello {

        /*
         * (non-Javadoc)
         * @see test.interface_.InterfaceTest.IHello#sayHello()
         */
        @Override
        public void sayHello() {
            System.out.println(a);
        }

        public String callDefault() {
            return "B" + haha();
        }

    }

    abstract class C implements IHello {

    }

}

class A implements IHello {



}