
package test.try_catch_finally;

/**
 * 【强制】不能在finally块中使用return，finally块中的return返回后方法结束执行，不会再执行catch块中的return语句。
 *
 * @author Shuaijun He
 */
public class ReturnTest {

    private int f() {
        try {
            int i = 12 / 0;
            System.out.println(" try return" + i);
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(" catch return");
            return 2 / 0;
        } finally {
            System.out.println("finally return");
//            return 3;
        }

    }

    public static void main(String[] args) {
        ReturnTest test = new ReturnTest();
        System.out.println(test.f());
    }
}
