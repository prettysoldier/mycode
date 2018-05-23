
package test.single;

/**
 * @author Shuaijun He
 */
public class Singleton4Eager {
    private static Singleton4Eager instance = null;
    static {
        Singleton4Eager.instance = new Singleton4Eager();
    }

    private Singleton4Eager() {
    }

    public static Singleton4Eager getInstance() {
        return Singleton4Eager.instance;
    }
}