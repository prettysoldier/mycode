package jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 下面是一个简单的hot swap类加载器实现。hot swap即热插拔的意思，这里表示一个类已经被一个加载器加载了以后，
 * 在不卸载它的情况下重新再加载它一次。我们知道Java缺省的加载器对相同全名的类只会加载一次，以后直接从缓存中
 * 取这个Class object。因此要实现hot swap，必须在加载的那一刻进行拦截，先判断是否已经加载，若是则重新加载一次，
 * 否则直接首次加载它。我们从URLClassLoader继承，加载类的过程都代理给系统类加载器URLClassLoader中的相应方法来完成。
 *
 * 可以重新载入同名类的类加载器实现
 * 放弃了双亲委派的加载链模式，需要外部维护重载后的类的成员变量状态
 * @author heshuaijun
 * @date 2019/10/5 15:31
 */
public class HotSwapClassLoader extends URLClassLoader {

    public HotSwapClassLoader(URL[] urls) {
        super(urls);
    }

    public HotSwapClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    // 下面的两个重载load方法实现类的加载，仿照ClassLoader中的两个loadClass()
    // 具体的加载过程代理给父类中的相应方法来完成
    public Class<?> load(String name) throws ClassNotFoundException {
        return load(name, false);
    }

    public Class<?> load(String name, boolean resolve) throws ClassNotFoundException {
        // 若类已经被加载，则重新再加载一次
        if (null != super.findLoadedClass(name)) {
            return reload(name, resolve);
        }
        // 否则用findClass()首次加载它
        Class<?> clazz = super.findClass(name);
        if (resolve) {
            super.resolveClass(clazz);
        }
        return clazz;
    }

    public Class<?> reload(String name, boolean resolve) throws ClassNotFoundException {
        return new HotSwapClassLoader(super.getURLs(), super.getParent()).load(
                name, resolve);
    }

    public static void main(String args[]) throws MalformedURLException {
        A a = new A();  // 加载类A
        B b = new B();  // 加载类B
        a.setB(b);  // A引用了B，把b对象拷贝到A.b
        System.out.printf("A classLoader is %s\n", a.getClass());
        System.out.printf("B classLoader is %s\n", b.getClass().getClassLoader());
        System.out.printf("A.b classLoader is %s\n", a.getB().getClass().getClassLoader());

        try {
            URL[] urls = new URL[]{ new URL("file://\\data\\idea_workspace\\mycode\\target\\classes\\jvm\\classloader\\") };
            HotSwapClassLoader c1 = new HotSwapClassLoader(urls, a.getClass().getClassLoader());
            Class clazz = c1.load("jvm.classloader.A");  // 用hot swap重新加载类A
            Object aInstance = clazz.newInstance();  // 创建A类对象
            Method method1 = clazz.getMethod("setB", B.class);  // 获取setB(B b)方法
            method1.invoke(aInstance, b);    // 调用setB(b)方法，重新把b对象拷贝到A.b
            Method method2 = clazz.getMethod("getB");  // 获取getB()方法
            Object bInstance = method2.invoke(aInstance);  // 调用getB()方法
            System.out.printf("Reloaded A.b classLoader is %s\n", bInstance.getClass().getClassLoader());
        } catch (MalformedURLException | ClassNotFoundException |
                InstantiationException | IllegalAccessException |
                NoSuchMethodException | SecurityException |
                IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}