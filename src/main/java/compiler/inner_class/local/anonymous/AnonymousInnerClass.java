package compiler.inner_class.local.anonymous;

import java.util.ArrayList;
import java.util.List;

/**
 * 匿名内部类是局部内部类的一种，因为匿名，而构造器的名字与类名必须相同，所以不能自己添加构造器，须要由编译器生成。
 *
 * @author hsj
 * @create 2019/12/25
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {

        int a = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
            int b = 2;

        }).start();

        // 静态方法中如何获取class
        new AnonymousInnerClass().f();
        System.out.println(new Object(){}.getClass().getEnclosingClass());
    }

    public void f(){
        System.out.println("getEnclosingClass : " + getClass().getEnclosingClass());
        g(new ArrayList<String>(){{add("one");add("two");}});
    }

    private void g(List<String> list){

    }
}
