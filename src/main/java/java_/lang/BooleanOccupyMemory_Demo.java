package java_.lang;

import java_.lang.instrument.PreMainTraceAgent;

/**
 * 关于boolean占用内存的研究：
 * - boolean[]和对象属性中的boolean占用1字节！
 * - 静态变量boolean占用内存，暂时不知 TODO 我认为很可能与实例属性一样，是1个字节。
 *
 * - 方法中的局部变量boolean，是一个slot大小。（可能是4个字节）
 *
 *
 * @author hsj
 * @create 2019/11/21
 */
public class BooleanOccupyMemory_Demo {

    public static void main(String[] args) {

//        booleanArrayUseByteLengthTest();
        booleanUseByteLengthTest();
    }

    /**
     * 测试boolean数组占用多少空间？
     * 先注释掉数组分配，测试JVM最小需要的内存大约是5m。
     * -Xmx15m -Xmx14m 分别测试，发现-Xmx15m时不报错，Xmx14m报OutOfMemoryError
     */
    private static void booleanArrayUseByteLengthTest() {
        int _10_MB = 1024 * 1024 * 10;
        // 先注释掉此行，测试JVM最小需要的内存大约是5m。
        boolean[] boolArray = new boolean[_10_MB];
        System.out.println("no excption");
    }

    /**
     * 测试局部变量boolean占用多少空间？
     * 从字节码中可以看到，boolean转变成 ICONST_1
     * 局部变量表的容量以变量槽（Slot）为最小单位。在虚拟机规范中并没有明确指明一个Slot应占用的内存空间大小
     * （允许其随着处理器、操作系统或虚拟机的不同而发生变化），一个Slot可以存放一个32位以内的数据类型：boolean、byte、char、short、int、float、reference和returnAddresss。
     * reference是对象的引用类型，returnAddress是为字节指令服务的，它执行了一条字节码指令的地址。
     * 对于64位的数据类型（long和double），虚拟机会以高位在前的方式为其分配两个连续的Slot空间。
     * 虚拟机通过索引定位的方式使用局部变量表，索引值的范围是从0开始到局部变量表最大的Slot数量，对于32位数据类型的变量，索引n代表第n个Slot，对于64位的，索引n代表第n和第n+1两个Slot。

     *
     *
     * -javaagent:D:\idea_workspace\mycode\out\artifacts\PreMainTraceAgent\PreMainTraceAgent.jar=asdf
     *
     */
    static boolean b = true;

    private static void booleanUseByteLengthTest() {

        boolean b1 = true;

        System.out.println("PreMainTraceAgent.sizeOf(new A()) : " + PreMainTraceAgent.sizeOf(new A()));
        System.out.println("PreMainTraceAgent.sizeOf(B.class) : " + PreMainTraceAgent.sizeOf(B.class));
        System.out.println("PreMainTraceAgent.sizeOf(true) : " + PreMainTraceAgent.sizeOf(true));

        System.out.println("PreMainTraceAgent.sizeOf(new Integer[0]) : " + PreMainTraceAgent.sizeOf(new Integer[0]));



    }
}

/**
 * -javaagent:D:\idea_workspace\mycode\out\artifacts\PreMainTraceAgent\PreMainTraceAgent.jar=asdf -XX:-UseCompressedOops
 *
 * 不添加boolean属性时，大小为16B（64位操作系统不压缩指令时，对象头是16B）
 *
 * 当定义8个属性及以内时，大小为24B
 * 当定义9个属性时，大小为32B
 *
 * 说明boolean类型实例变量，占用1B。
 */
class A{
    private boolean b1 = true;
    boolean b2 = true;
    boolean b3 = true;
    boolean b4 = false;
    boolean b5;
    boolean b6 = true;
    boolean b7 = true;
    boolean b8 = true;
    boolean b9 = true;
    /**
     * reference类型在32位系统上每个占用4bytes, 在64位系统上每个占用8bytes。
     * 开启指针压缩后占用4个字节，默认是开启的
     */
//    Object o;
}

/**
 * 不管加不加静态属性，B.class 的大小都是498B。
 */
class B{
    static boolean b1 = true;
    static boolean b2 = true;
    static boolean b3 = true;
    static boolean b4 = false;
    static boolean b5;
    static boolean b6 = true;
    static boolean b7 = true;
    static boolean b8 = true;
    static boolean b9 = true;
}
