/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java8.call;

/**
 * @author Shuaijun He
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}