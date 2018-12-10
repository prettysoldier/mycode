
package test.jvm;

/**
 * 代码重排
 *
 * @author Shuaijun He
 */
public class ReCodeSort1 {
    int a = 0, b = 0;

    public static void main(String[] args) {
        ReCodeSort1 codeSort1 = new ReCodeSort1();
        for (;;) {
            codeSort1.a = codeSort1.a + 1;
            codeSort1.b = codeSort1.b + 1;

        }
    }
}
