package source_code_java.java_.syntax.keyword.synchronized_;

/**
 * Synchronized的可重入性：1）同一把锁可重复进入。
 *   2）父子问题：不是一把锁！
 * @author heshuaijun
 * @date 2020/1/28 21:50
 */
public class SynchronizedReentrant {
    public static void main (String[] args) {
        new Child().d();
    }
}

class Parent{
    synchronized void f(){
        System.out.println("parent");
    }
}

class Child extends Parent{
    synchronized void d(){
        System.out.println("child");
        super.f();
    }
}