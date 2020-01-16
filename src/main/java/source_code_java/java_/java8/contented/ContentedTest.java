package source_code_java.java_.java8.contented;


/**
 * 执行时，必须加上虚拟机参数-XX:-RestrictContended，@Contended注释才会生效。很多文章把这个漏掉了，那样的话实际上就没有起作用。
 * https://yq.aliyun.com/articles/609962
 * 不优化时32.534577733秒，加上@Contended注解后9.699835227秒，快了两倍多
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class ContentedTest implements Runnable{
    // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    public static int NUM_THREADS = 4;
    private final int arrayIndex;
    private static VolatileLong[] longs;

    public ContentedTest(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        Thread.sleep(10000);
        System.out.println("starting....");
        if (args.length == 1) {
            NUM_THREADS = Integer.parseInt(args[0]);
        }

        longs = new VolatileLong[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ContentedTest(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }
}


//@Contenttend
class VolatileLong {
    public volatile long value = 0L;
}