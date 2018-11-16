package test.java.concurrent;

public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> TL_INT = ThreadLocal.withInitial(() -> 6);

    private static final ThreadLocal<Integer> a = new ThreadLocal<>();
    private static final ThreadLocal<String> TL_STRING =
            ThreadLocal.withInitial(() -> "Hello, world");

    /**
     *
     * @param args
     */
    public static void main(String... args) {
        // 6
        System.out.println(TL_INT.get());
        TL_INT.set(TL_INT.get() + 1);
        // 7
        System.out.println(TL_INT.get());
        TL_INT.remove();
        // 会重新初始化该value，6
        System.out.println(TL_INT.get());

        a.set(1);
        a.remove();
        System.out.println(a.get());

        A a1 = new A(2);
        A a2 = new A(3);
        System.out.println(a1.getLocal().get());
        System.out.println(a2.getLocal().get());
    }
}

class A {
    private ThreadLocal<Integer> local = new ThreadLocal<>();

    /**
     *
     * @param i
     */
    public A(int i) {
        local.set(i);
    }

    public ThreadLocal<Integer> getLocal() {
        return local;
    }
}

