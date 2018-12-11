
package pattern.single;

/**
 * 双重检查锁定
 * 
 * @author Shuaijun He
 */
public class Singleton4DoubleCheck {

    private volatile static Singleton4DoubleCheck singleton;

    private Singleton4DoubleCheck() {
    }

    public static Singleton4DoubleCheck getSingleton() {

        if (Singleton4DoubleCheck.singleton == null) {
            synchronized (Singleton4DoubleCheck.class) {

                if (Singleton4DoubleCheck.singleton == null) {
                    Singleton4DoubleCheck.singleton = new Singleton4DoubleCheck();
                }
            }
        }
        return Singleton4DoubleCheck.singleton;
    }
}
