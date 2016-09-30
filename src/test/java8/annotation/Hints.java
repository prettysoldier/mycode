/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java8.annotation;

import java.lang.annotation.Repeatable;

/**
 * @author Shuaijun He
 */
public @interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}