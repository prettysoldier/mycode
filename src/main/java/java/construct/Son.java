/**
 * 
 */
package java.construct;

import java.construct.a.Parent;

/**
 * 父子之间的构造器，可以不一样（主要指参数）。父子构造器之间没有继承关系
 * 
 * @author shuaijunhe
 */
public class Son extends Parent {

    static int sonStaticInt = 10;

    static {
        System.out.println("Son static code block...");
        System.out.println("sonStaticInt=" + sonStaticInt);
    }

    static void m(){
        System.out.println("Son static method...");
    }

    int sonInt = 10;

    {
        System.out.println("Son code block...");
    }

    Son a;

    public Son(){
//        System.out.println("A before");
        // 必须是第一行
        super(1);
        Son.m();
        a = this;
        System.out.println("a=" + a.g(a));
        System.out.println("Son constrct");
        System.out.println("sonInt=" + sonInt);
//        throw new Exception("错误");
    }


    public Son(int a, int b) {

        super(a);
    }

    public Son(int a, int b, int c) {

        this(a, b);

    }


    public static void main(String[] args) {

        try {
            new Son().f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String f(){
        return "f_method";
    }
    private String g(Son s){
        return s.f();
    }

    @Override
    public String toString() {
        return "son" + this.a.sonInt;
    }
}

