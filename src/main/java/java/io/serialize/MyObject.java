
package java.io.serialize;

import java.io.Serializable;

/**
 * @author Shuaijun He
 */
public class MyObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6237902585649042449L;

    private MyObject() {
    }

    private static class MyObjectHolder {
        private static MyObject myObject = new MyObject();
    }

    public static MyObject getInstance() {
        return MyObjectHolder.myObject;
    }

    /**
     * 神奇方法，有了这个方法，在反序列化时能实现单例
     *
     * @author Shuaijun He
     * @return
     */
    protected Object readResolve() {
        System.out.println("调用了readResolve()方法");
        return MyObjectHolder.myObject;
    }
}
