
package test.jvm;

/**
 * 虚拟机参数 -verbose:gc 输出详细的gc过程
 * 结论：
 *
 * @author Shuaijun He
 */
public class LocalVariableTable {
    public static void main(String[] args) {
        LocalVariableTable lvt = new LocalVariableTable();
//        lvt.f1();
//        lvt.f2();
        lvt.f4();
    }

    /**
     * 内存没有被回收
     *
     * @author Shuaijun He
     */
    public void f1() {

        byte[] pp = new byte[64 * 1024 * 1024];
        System.out.println(pp);
        System.gc();
    }

    /**
     * 内存没有被回收
     *
     * @author Shuaijun He
     */
    public void f2() {
        {
            byte[] pp = new byte[64 * 1024 * 1024];
            System.out.println(pp);
        }
        System.gc();
    }

    /**
     * 内存被回收
     * [GC (System.gc()) 67440K->66272K(121856K), 0.0025575 secs]
     * [Full GC (System.gc()) 66272K->551K(121856K), 0.0065091 secs]
     *
     * @author Shuaijun He
     */
    public void f3() {

        {
            byte[] pp = new byte[64 * 1024 * 1024];
            System.out.println(pp);
        }
        int a = 0;
        System.out.println(a);
        System.gc();
    }

    /**
     * 内存被回收
     * [GC (System.gc()) 67440K->66272K(121856K), 0.0025575 secs]
     * [Full GC (System.gc()) 66272K->551K(121856K), 0.0065091 secs]
     *
     * @author Shuaijun He
     */
    public void f4() {

        byte[] pp = new byte[64 * 1024 * 1024];
        pp = null;
        System.out.println(pp);
        System.gc();
    }

}
