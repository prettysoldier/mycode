
package test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Shuaijun He
 */
public class DynamicProxyTest {

    interface IHello {
        String sayHello();
    }

    static class Hello implements IHello {

        /*
         * (non-Javadoc)
         * @see test.proxy.DynamicProxyTest.IHello#sayHello()
         */
        @Override
        public String sayHello() {
            // TODO Auto-generated method stub
            System.out.println("hello world");
            return "good man";
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        /**
         * 为original 生成代理对象
         *
         * @author Shuaijun He
         * @param original
         * @return
         */
        Object bind(Object original) {
            this.originalObj = original;
            return Proxy.newProxyInstance(this.originalObj.getClass()
                .getClassLoader(), this.originalObj.getClass().getInterfaces(),
                this);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
         * java.lang.reflect.Method, java.lang.Object[])
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("welcome");
            Object retObj = method.invoke(this.originalObj, args);
            System.out.println("bye~");
            return retObj;
        }
    }

    public static void main(String[] args) {
        System.getProperties().put(
            "sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        System.out.println(hello.equals(new Object()));
        System.out.println(hello.toString());
        System.out.println(hello.hashCode());
        System.out.println(hello.sayHello());
    }
}
