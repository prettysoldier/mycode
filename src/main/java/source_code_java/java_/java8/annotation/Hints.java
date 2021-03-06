
package source_code_java.java_.java8.annotation;

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