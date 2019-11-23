package java_.try_catch_finally;

import java.io.Closeable;

/**
 * 主要实现了 AutoCloseable 接口，就可以使用 try-resouece语法
 * @author heshuaijun
 * @date 2019/11/24 1:01
 */
public class TryWithResourceTest {


    public static void main (String[] args) {

        try(A a = new A()){
            System.out.println(a + "做点事情");
        }

        try(B b = new B()){
            System.out.println(b + "做点事情");
        }
        // 编译报错
//        try(C c = new C()){
//            System.out.println();
//        }
    }

    static class A implements Closeable {
        @Override
        public void close () {
            System.out.println("A 资源释放");
        }
    }

    static class B implements AutoCloseable {
        @Override
        public void close () {
            System.out.println("B 资源释放");
        }
    }

    static class C {
        public void close () {
            System.out.println("C 资源释放");
        }
    }
}
