
package java_.interface_;

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

    /**
     * 默认是静态的！！
     */
    interface IHello {
        int a = 2;

        void sayHello() throws Exception;

        void sort(Comparable[] arr);

    }

    interface IHello2 extends IHello {
        /**
         *
         */
        @Override
        void sayHello();
    }



}

