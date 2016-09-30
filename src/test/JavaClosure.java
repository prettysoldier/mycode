/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test;

/**
 * @author Shuaijun He
 */
public class JavaClosure {

    protected void run(final TestCase test) {
        System.out.println();
        /*
         * Protectable p = new Protectable() {
         * @Override
         * public void protect() {
         * test.runBare();
         * System.out.println(test);
         * }
         * };
         * this.runProtected(p);
         */

        test.runBare();
    }

    private void runProtected(Protectable p) {
        p.protect();
    }

    public static void main(String[] args) {
        new JavaClosure().run(new TestCase());
    }
}

interface Protectable {
    public void protect();
}

class TestCase {

    public void runBare() {
        System.out.println("dsfgdf");
    }
}
