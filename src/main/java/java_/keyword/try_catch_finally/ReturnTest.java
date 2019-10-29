
package java_.keyword.try_catch_finally;

/**
 * finally块中return的问题
 * 【强制】不能在finally块中使用return，finally块中的return返回后方法结束执行，不会再执行catch块中的return语句。
 * @author Shuaijun He
 */
public class ReturnTest {

    public int f() {
        int x;
        try {
            // try 中报错，会返回x=2，原因是return 会先将返回值存下，finally修改x后，程序会直接将预存的返回值直接返回。
            x = 1 / 0;
            System.out.println(" try return" + x);
            // 如果出现Exception以外的异常，方法异常退出，没有返回值
//            throw new Error();
            return x;
        } catch (Exception e) {
            System.out.println("catch");
            x = 2;
            return x;
        } finally {
            System.out.println("finally");
            x = 3;
            //【强制】不能在finally块中使用return，finally块中的return返回后方法结束执行，不会再执行catch块中的return语句。
            return x;
        }

    }

    public static void main(String[] args) {
        ReturnTest test = new ReturnTest();
        System.out.println(test.f());
    }
}
