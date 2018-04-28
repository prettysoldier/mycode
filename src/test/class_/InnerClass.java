/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.class_;

/**
 * @author Shuaijun He
 */
public class InnerClass {

    public void f() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

            }
        }).start();
    }

    private class Inner {
    }
}
