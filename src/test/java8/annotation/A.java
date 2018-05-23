
package test.java8.annotation;

import java.lang.annotation.Annotation;

/**
 * @author Shuaijun He
 */
@Hints({ @Hint("hit1"), @Hint("hit2") })
public class A {

    public static void main(String[] args) {
        Hint hint = A.class.getAnnotation(Hint.class);
        System.out.println(hint);          // null

        Hint[] hints1 = A.class.getAnnotationsByType(Hint.class);
        System.out.println(hints1.length);          // 2

        Annotation[] hints2 = A.class.getAnnotations();
        System.out.println(hints2.length);          // 2

        Hints hints3 = A.class.getAnnotation(Hints.class);
        System.out.println(hints3.value().length);  // 2
    }

}
