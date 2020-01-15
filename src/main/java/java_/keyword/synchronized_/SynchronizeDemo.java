package java_.keyword.synchronized_;

/**
 * @Desc 1 验证哪些类型的方法不在字节码中，private方法不在：不是不在，而是没有显示，使用javap -p 即可！！
 * 用法: javap <options> <classes>
 * 其中, 可能的选项包括:
 *   -help  --help  -?        输出此用法消息
 *   -version                 版本信息
 *   -v  -verbose             输出附加信息
 *   -l                       输出行号和本地变量表
 *   -public                  仅显示公共类和成员
 *   -protected               显示受保护的/公共类和成员
 *   -package                 显示程序包/受保护的/公共类
 *                            和成员 (默认)
 *   -p  -private             显示所有类和成员
 *   -c                       对代码进行反汇编
 *   -s                       输出内部类型签名
 *   -sysinfo                 显示正在处理的类的
 *                            系统信息 (路径, 大小, 日期, MD5 散列)
 *   -constants               显示最终常量
 *   -classpath <path>        指定查找用户类文件的位置
 *   -cp <path>               指定查找用户类文件的位置
 *   -bootclasspath <path>    覆盖引导类文件的位置
 *
 *
 * 2）synchronized关键字 在字节码中的表达
 * @Author shuaijunhe
 * @CreateTime 2018/11/30 19:18
 **/
public class SynchronizeDemo {

    public void f(){
        int i = 1;
    }

    void f1(){
        int i = 1;
    }

    protected  void f2(){
        int i = 1;
    }

    /**
     * 使用javap -p
     */
    private  void f3(){
        int i = 1;
    }

    public final  void f4(){
        int i = 1;
    }

    public static  void f5(){
        int i = 1;
    }

    /**
     * 使用javap -p
     */
    private static  void f6(){
        int i = 1;
    }

    /**
     * public synchronized void test();
     */
    public synchronized void test(){
        int i = 2;
    }

    /**
     * 3: monitorenter
     * 4: iconst_2
     * 5: istore_2
     * 6: aload_1
     * 7: monitorexit
     */
    public void test1(){

        synchronized(this){
            int i = 2;
            ++i;
        }
    }
}
