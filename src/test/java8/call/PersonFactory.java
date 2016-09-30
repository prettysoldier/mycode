/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java8.call;

/**
 * @author Shuaijun He
 */
interface PersonFactory<P extends Person> {

    P createDefault();

//    P create(String firstName, String lastName);
}
