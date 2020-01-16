package source_code_java.java_.io;

import java.io.Console;

/**
 * 由于Scanner在控制台中明文显示，所以jdk6提供了Console
 * TODO 获取不到Console对象。eclipse、IDEA 占用了jvm 的控制台
 * @author hsj
 *
 * @create 2019/11/21
 */
public class Console_Demo {

    public static void main(String[] args) {

        Console console = System.console();

        String username = console.readLine("user name: ");
        char[] passwd = console.readPassword("pass : ");

        System.out.println("username : " + username);
        System.out.println("pass : " + String.valueOf(passwd));
    }
}
