package java_.try_catch_finally;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 18:10
 **/
public class TryCatchFinally {

    public static void main(String[] args) {

        System.out.println(finallyNotWork());

        tryWithResource();
    }

    /**
     * finally 在return 表达式运行之后执行。
     * 此时 return 的值 已经存到了一个新的局部变量中，
     * 所以finally 并不会修改return 的值。
     *
     * finally 中有return语句，情况就不同了，会再加载一遍temp值返回，
     * 所以【强制】不要在finally中写返回语句
     * @return
     */
    public static int finallyNotWork(){
        int i = 1;
        try {
            throw new Exception();
        } catch (Exception e){
            return ++i;
        }
        finally {
            i = 99;
//            return i;
        }
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

        try( Scanner in = new Scanner(new FileInputStream("/cmd.txt"));
             PrintStream out = new PrintStream("out.txt"))
        {
            // 此行报错，说明，try-with-resource中声明的变量会被隐式地加上final关键字，所以无法再进行赋值。
//            in = new Scanner(new FileInputStream(""));
            while(in.hasNext()){
                out.println(in.next().toUpperCase());
            }
            throw new Exception("asd");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.addSuppressed(new Exception("自己加入的被抑制异常1"));
            e.addSuppressed(new Exception("自己加入的被抑制异常2"));
            System.out.println(e.getSuppressed()[1]);
        }
    }

}
