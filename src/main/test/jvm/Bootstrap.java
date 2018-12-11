package main.test.jvm;

import sun.misc.Launcher;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/28 19:11
 **/
public class Bootstrap {
    public static void main(String[] args) {
        System.out.println(Launcher.getLauncher().getClassLoader().getParent());
        try {
            Class c = Class.forName("java.lang.Integer");
            System.out.println(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
