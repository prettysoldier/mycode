
package test.java8.annotation;


/**
 * @author Shuaijun He
 */
@Hint("hit1")
@Hint("hit2")
public class B {

    public static void main(String[] args) {
        Hint hint = B.class.getAnnotation(Hint.class);
        System.out.println(hint);          // null
        Hints hints1 = B.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2
        Hint[] hints2 = B.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);

    }

    @Hint("hit1")
    @Hint("hit2")
    public void f() {

    }
}
