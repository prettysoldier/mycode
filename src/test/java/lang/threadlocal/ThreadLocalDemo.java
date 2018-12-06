package test.java.lang.threadlocal;

/**
 * @Desc
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
