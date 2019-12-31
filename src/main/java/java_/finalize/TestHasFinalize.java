
package java_.finalize;

/**
 * @author Shuaijun He
 */
public class TestHasFinalize {

    public static void main(String[] args) {
        while (true) {
            new TestHasFinalize();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize.");
    }
}
