
package test.interface_;

import test.interface_.InterfaceTest.IHello;

/**
 * 可见性与其他class相同
 * only public, protected, private, abstract & static are permitted
 * 其中 static 和 abstract 是多余的
 *
 * @author Shuaijun He
 */
public class InterfaceTest {

    interface IHello {
        void sayHello();

        default String haha() {
            return "haha";
        }
    }

    class B implements IHello {

        /*
         * (non-Javadoc)
         * @see test.interface_.InterfaceTest.IHello#sayHello()
         */
        @Override
        public void sayHello() {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see test.interface_.InterfaceTest.IHello#haha()
         */
        @Override
        public String haha() {
            // TODO Auto-generated method stub
            return "B" + IHello.super.haha();
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