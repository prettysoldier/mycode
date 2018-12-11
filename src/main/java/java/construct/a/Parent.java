package main.test.java.construct.a;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/23 23:09
 **/
public abstract class Parent
{


    static {
        System.out.println("Parent static code block...");
//        System.out.println("parentStaticInt=" + parentStaticInt);
    }
    static int parentStaticInt = 10;



    {
        System.out.println("Parent code block ...");
    }
    int parentInt = 10;

    protected Parent(int b) {
        System.out.println("Parent construct " + b);
        System.out.println("parentInt=" + parentInt);
    }
}
