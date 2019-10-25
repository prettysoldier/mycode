
package jvm.stack;

import java_.util.concurrent.executors.MyThreadFactory;

import java.lang.reflect.Field;

/**
 * 虚拟机栈的高度称为栈的深度，栈深度受栈帧大小影响。也受-Xss参数的影响（默认1M，如果设置得太小会被忽略）
 * （在栈中存放局部变量，参数，运行中间结果）
 * 每次运行的结果不同
 *
 *
 * @author Shuaijun He
 */
public class JVMStackTest {
    int count = 0;

    /**
     * java.lang.StackOverflowError
     * stack height:18562
     *
     * @author Shuaijun He
     */
    public void testStack() {
        this.count++;
        this.testStack();
    }

    /**
     * 增加参数
     * java.lang.StackOverflowError
     * stack height:17478
     *
     * @author Shuaijun He
     * @param a
     * @param b
     */
    public void testStack(int a, int b) {
        this.count++;
        this.testStack(a, b);
    }

    /**
     * 增加局部变量 数量
     * java.lang.StackOverflowError
     * stack height:7845
     *
     * -Xss=100k 时 stack height:665
     *
     * @author Shuaijun He
     * @param a
     * @param b
     */
    public void testStackWithLocalVar(int a, int b) {
        int c = 5;
        long d = 4L;
        System.out.println(c + d);
        this.count++;
        this.testStackWithLocalVar(a, b);
    }

    /**
     * Thread.stackSize = 2g-1 时 stack height:134191969
     * Thread.stackSize = 128m 时 stack height:8204399 (此行往上，删掉System.out.println(c + d);否则太慢了)
     * Thread.stackSize = 4m 时 stack height:32408
     * Thread.stackSize = 2m 时 stack height:16028
     * Thread.stackSize = 1.5m 时 stack height:11936
     * Thread.stackSize = 1m+1 时 stack height:8353
     * Thread.stackSize = 1m 时 stack height:7833
     * Thread.stackSize = 512k+1 时 stack height:4242
     * Thread.stackSize = 512k 时 stack height:3738
     * Thread.stackSize = 256k 时 stack height:1687 （设置 -Xss512k， stack height值不变）
     * Thread.stackSize = 128k+1 时 stack height:1168
     *
     * Thread.stackSize = 128k 时 stack height:661
     * Thread.stackSize = 112k 时 stack height:660
     * Thread.stackSize = 96k 时 stack height:656
     * Thread.stackSize = 80k 时 stack height:669
     * Thread.stackSize = 72k 时 stack height:659
     * Thread.stackSize = 64k+1 时 stack height:662
     *
     * Thread.stackSize = 64k 时 stack height:156
     * Thread.stackSize = 32k 时 stack height:150
     * Thread.stackSize = 16k 时 stack height:156
     * Thread.stackSize = 8k 时 stack height:150
     * Thread.stackSize = 5k 时 stack height:156
     * Thread.stackSize = 4k+1 时 stack height:146
     *
     * Thread.stackSize = 4k 时 stack height:7837
     * Thread.stackSize = 2k 时 stack height:7838
     * Thread.stackSize = 1k 时 stack height:7831
     * Thread.stackSize = 1 时 stack height:7836  （设置 -Xss512k， stack height= 7834）
     *
     * Thread.stackSize = 0 时 stack height:7839  （设置 -Xss512k， stack height= 3737）
     * Thread.stackSize = -1 时 stack height:7835 （设置 -Xss512k， stack height= 3737）
     * 总结：
     * stackSize <= 4k 会忽略此参数，取默认值 1m
     * stackSize 属于 (4k, 64k] 取4k
     * stackSize 属于 (64k, 128k] 取64k
     * stackSize 属于 (128k, Max) 取stackSize。没找到上限！
     * 注意：同时设置 -Xss 参数和 Thread.stackSize > 0时，以Thread.stackSize为准；Thread.stackSize <=0 时, 以 -Xss 为准
     *
     */
    public static void testMyThreadStackSize(){
        // 设置 Thread.stackSize = 100k
        MyThreadFactory.getMyThreadFactory().newThread(()->{
            JVMStackTest test = new JVMStackTest();
            try {
                test.testStackWithLocalVar(1, 2);
            } catch (Throwable e) {
                System.out.println(e);
                System.out.println("stack height: " + test.count);
                try {
                    Field stackSizeField = Thread.class.getDeclaredField("stackSize");
                    stackSizeField.setAccessible(true);
                    long stackSize = stackSizeField.getLong(Thread.currentThread());
                    System.out.println("stackSize(kb): " + (stackSize / 1024) + ", " + stackSize);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 测试 -Xss 的影响
     */
    private static void jvmXss(){
        JVMStackTest test = new JVMStackTest();
        try {
//            test.testStack();
//            test.testStack(1, 2);
            test.testStackWithLocalVar(1, 2);
        } catch (Throwable e) {
            System.out.println(e);
            System.out.println("stack height:" + test.count);
        }
    }

    public static void main(String[] args) {

//        jvmXss();
        testMyThreadStackSize();
    }

}
