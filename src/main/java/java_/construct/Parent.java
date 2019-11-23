package java_.construct;

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

    private int b1;
    protected Parent(int b) {
        System.out.println("Parent construct " + b);
        System.out.println("parentInt=" + parentInt);
        b1 = b;
    }
}
