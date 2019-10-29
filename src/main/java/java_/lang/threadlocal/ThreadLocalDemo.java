package java_.lang.threadlocal;

/**
 * Thread-Specific Storage 模式-----ThreadLocal 线程独有的存储库
 *
 * 每一个线程都有一个ThreadLocal.ThreadLocalMap，ThreadLocalMap底层由数组实现：Entry[] table。
 *   Entry继承了WeakReference，以ThreadLocal作为引用，也称为键。
 * 当线程没有ThreadLocalMap时，在ThreadLocal初次get的时候会创建。
 * 如果ThreadLocal的初始值是null。一般会调用 ThreadLocal.withInitial(), set()，或者复写initialValue()
 *
 *
 *
 * @Author shuaijunhe
 * @CreateTime 2018/12/5 20:37
 **/
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> threadLocalInt = ThreadLocal.withInitial(() -> 2);
    private static final ThreadLocal<String> threadLocalString = ThreadLocal.withInitial(() -> "Hello world");

    public static void main(String... args) {
        System.out.println(Thread.currentThread().getName() + ", " + threadLocalInt.get());
        threadLocalInt.set(10);
        System.out.println(Thread.currentThread().getName() + ", " + threadLocalInt.get());

        threadLocalInt.remove();
        // 会重新初始化
        System.out.println(Thread.currentThread().getName() + ", " + threadLocalInt.get());
        new MyThread().start();

    }

    private static class MyThread extends Thread{
        @Override
        public void run() {

            System.out.println(this.getName() + ", " + threadLocalInt.get());
            threadLocalInt.set(100);
            System.out.println(Thread.currentThread().getName() + ", " + threadLocalInt.get());
        }
    }

}
