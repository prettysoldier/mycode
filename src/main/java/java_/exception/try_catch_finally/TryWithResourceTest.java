package java_.exception.try_catch_finally;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 主要实现了 AutoCloseable 接口，就可以使用 try-resouece语法
 * @author heshuaijun
 * @date 2019/11/24 1:01
 */
public class TryWithResourceTest {


    public static void main (String[] args) {
        tryWithResource();

        try(A a = new A()){
            System.out.println(a + "做点事情");
        }

        try(B b = new B()){
            System.out.println(b + "做点事情");
        }

        try(A a = new A(); B b = new B()){
            System.out.println(a + "做点事情");
            System.out.println(b + "做点事情");
        }
        // 编译报错
//        try(C c = new C()){
//            System.out.println();
//        }


    }

    /**
     * 使用try-with-resources的好处是可以简化代码，自动关闭资源，如果关闭资源时报错，也可以优雅地处理异常。
     * 机制是关闭时抛出的异常会被抑制！如果想得到此异常，可以通过 getSuppressed() 方法获取。
     *
     * getSuppressed 是 Throwable 类的一个方法，会返回一个数组。
     *  * Returns an array containing all of the exceptions that were suppressed。线程安全的。
     *
     */
    private static void tryWithResource(){

        try(Scanner in = new Scanner(new FileInputStream("/cmd.txt"));
            PrintStream out = new PrintStream("out.txt"))
        {
            // 此行报错，说明，try-with-resource中声明的变量会被隐式地加上final关键字，所以无法再进行赋值。
//            in = new Scanner(new FileInputStream(""));
            while(in.hasNext()){
                out.println(in.next().toUpperCase());
            }
            throw new Exception("asd");
        }catch (Exception e){
            e.addSuppressed(new Exception("自己加入的被抑制异常1"));
            e.addSuppressed(new Exception("自己加入的被抑制异常2"));
            System.out.println(Arrays.toString(e.getSuppressed()));
        }
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
