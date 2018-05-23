
package test;

/**
 * @author Shuaijun He
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {

//        Object a = new Object();
//        Object se = a.clone();
//        System.out.println(a + "|" + se);

        A aa = new A();
        A aa2 = (A) aa.clone();
        System.out.println(aa + "|" + aa2);
    }

    static class A implements Cloneable {
        /*
         * (non-Javadoc)
         * @see java.lang.Object#clone()
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
