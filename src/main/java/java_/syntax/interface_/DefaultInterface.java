package java_.syntax.interface_;

/**
 * java不提供多继承是因为多继承会大大增加复杂度，那么default interface 是不是违背了这个原则呢？
 * 我们试验一下：
 *   只实现一个接口时，默认接口方法无需具体实现，运行正常！
 *   实现两个接口时，默认接口方法签名相同，必须实现此接口，不能使用默认代码，否则编译不过！
 *
 * @author hsj
 * @create 2019/12/24
 */
public class DefaultInterface implements MyInterface1, MyInterface2{

    public static void main(String[] args) {
        new DefaultInterface().f();
    }

    @Override
    public void f() {
        System.out.println("DefaultInterface");
    }
}

interface MyInterface1{

    default void f(){
        System.out.println("MyInterface1");
    }
}
interface MyInterface2{

    default void f(){
        System.out.println("MyInterface2");
    }
}