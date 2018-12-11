
package java8.defaultInterface;

/**
 * @author Shuaijun He
 */
public interface Formula {

    int sdf();

    default int test() {
        System.out.println("111");
        return 1;
    }

    public static void main(String[] args) {

        Formula formula = new Formula() {

            @Override
            public int sdf() {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        formula.test();

    }
}
