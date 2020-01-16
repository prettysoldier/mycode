package java_.syntax.keyword;

import java.util.concurrent.CountDownLatch;

/**
 * volatile 有三个作用：
 *   1.防止重排序(有序性)
 *     先来了解一下Java中的happen-before规则：如果a happen-before b，则a所做的任何操作对b是可见的。
 *     （这一点大家务必记住，因为happen-before这个词容易被误解为是时间的前后）
 *     1）同一个线程中的，前面的操作 happen-before 后续的操作。（即单线程内按代码顺序执行。但是，在不影响在单线程环境执行结果的前提下，
 *          编译器和处理器可以进行重排序，这是合法的。换句话说，这一是规则无法保证编译重排和指令重排）。
 *     2）Synchronized 规则：监视器上的解锁操作 happen-before 其后续的加锁操作。（Synchronized 规则）
 *     3）volatile 规则：对volatile变量的写操作 happen-before 后续的读操作。（volatile 规则）
 *     4）线程启动规则：线程的start() 方法 happen-before 该线程所有的后续操作。（线程启动规则）
 *     5）线程所有的操作 happen-before 其他线程在该线程上调用 join 返回成功后的操作。
 *     6）传递性：如果 a happen-before b，b happen-before c，则a happen-before c（传递性）。
 *   2.实现可见性：
 *   3.保证原子性：volatile只能保证对单次读/写的原子性
 *               因为long和double两种数据类型的操作可分为高32位和低32位两部分，因此普通的long或double类型读/写可能不是原子的。
 *               因此，鼓励大家将共享的long和double变量设置为volatile类型，这样能保证任何情况下对long和double的单次读/写操作都具有原子性。
 * @author shuaijunhe
 * @create 2019/10/29
 * @description
 */
public class Volatile_Demo {
    /**
     * 我们从一个最经典的例子来分析重排序问题。大家应该都很熟悉单例模式的实现，而在并发环境下的单例实现方式，
     * 我们通常可以采用双重检查加锁（DCL）的方式来实现
     *
     * 现在我们分析一下为什么要在变量singleton之间加上volatile关键字。要理解这个问题，先要了解对象的构造过程，实例化一个对象其实可以分为三个步骤：
     *
     * 　　（1）分配内存空间。
     *
     * 　　（2）初始化对象。
     *
     * 　　（3）将内存空间的地址赋值给对应的引用。
     *
     * 但是由于操作系统可以对指令进行重排序，所以上面的过程也可能会变成如下过程：
     *
     * 　　（1）分配内存空间。
     *
     * 　　（2）将内存空间的地址赋值给对应的引用。
     *
     * 　　（3）初始化对象
     *
     * 如果是这个流程，多线程环境下就可能将一个未初始化的对象引用暴露出来，从而导致不可预料的结果。
     * 因此，为了防止这个过程的重排序，我们需要将变量设置为volatile类型的变量。
     */
    static class Singleton {

        public static volatile Singleton singleton;
        private long date = System.nanoTime();

        private Singleton (){}

        public static Singleton getInstance(){
            if(singleton == null){
                synchronized (Singleton.class){
                    if(singleton == null){
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }

        public long getDate() {
            return date;
        }


    }
    static class Main extends Thread {

        private CountDownLatch latch;

        public Main(CountDownLatch latch) {
            this.latch = latch;
        }

        public static void main(String[] args) {
            int n = 1000;
            CountDownLatch latch = new CountDownLatch(1);

            for(int i = 0; i< n; i++){
                new Main(latch).start();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("放行！");
            latch.countDown();
        }
        @Override
        public void run() {
            try {
                this.latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + Singleton.getInstance().getDate());
        }
    }

    /**
     * 为什么会出现b=3;a=1这种结果呢？正常情况下，如果先执行change方法，再执行print方法，输出结果应该为b=3;a=3。
     * 相反，如果先执行的print方法，再执行change方法，结果应该是 b=2;a=1。那b=3;a=1的结果是怎么出来的？
     * 原因就是第一个线程将值a=3修改后，但是对第二个线程是不可见的，所以才出现这一结果。
     * 如果将a和b都改成volatile类型的变量解决不了这个问题！！
     */
    static class VolatileVisable {
        volatile int a = 1;
        volatile int b = 2;

        public void change(){
            a = 3;
            b = a;
        }

        public void print(){
//            System.out.println("b="+b+";a="+a);
            System.out.println("a=" + a + "; b=" + b);
        }

        public static void main(String[] args) {
            while (true){
                final VolatileVisable test = new VolatileVisable();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test.change();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test.print();
                    }
                }).start();

            }
        }
    }

    /**
     * i++其实是一个复合操作，包括三步骤：
     *
     * 　　（1）读取i的值。
     *
     * 　　（2）对i加1。
     *
     * 　　（3）将i的值写回内存。
     *
     * volatile是无法保证这三个操作是具有原子性的，我们可以通过AtomicInteger或者Synchronized来保证+1操作的原子性。
     */
    static class Atomicity{
        volatile int i;

        public void addI(){
            i++;
        }

        public static void main(String[] args) throws InterruptedException {
            final  Atomicity test01 = new Atomicity();
            for (int n = 0; n < 1000; n++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test01.addI();
                    }
                }).start();
            }

            //等待10秒，保证上面程序执行完成
            Thread.sleep(10000);

            System.out.println(test01.i);
        }
    }

    static class MemoryBarrier {
        int a, b;
        volatile int v, u;

        void f() {
            int i, j;

            i = a;
            j = b;
            i = v;
            //LoadLoad
            j = u;
            //LoadStore
            a = i;
            b = j;
            //StoreStore
            v = i;
            //StoreStore
            u = j;
            //StoreLoad
            i = u;
            //LoadLoad
            //LoadStore
            j = b;
            a = i;
        }
    }
}
