
package test.java.interface_;

import test.java.interface_.InterfaceTest.IHello;

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

        void sayHello() throws Exception;

    }

    interface IHello2 extends IHello {

        @Override
        void sayHello();
    }

    class B implements IHello {

        /*
         * (non-Javadoc)
         * @see test.interface_.InterfaceTest.IHello#sayHello()
         */
        @Override
        public void sayHello() {
            // TODO Auto-generated method stub
            System.out.println(a);
        }


    }

    abstract class C implements IHello {

    }

}

class A implements IHello {

    /*
     * (non-Javadoc)
     * @see test.interface_.InterfaceTest.IHello#sayHello()
     */
    @Override
    public void sayHello() {
        // TODO Auto-generated method stub

    }

}