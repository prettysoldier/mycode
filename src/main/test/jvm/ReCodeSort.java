
package main.test.jvm;

/**
 * 代码重排
 *
 * @author Shuaijun He
 * @CreateTime
 */
public class ReCodeSort {
    private static int x = 0, y = 0;
    private static volatile int a = 0, b = 0;// volatile可修复此问题

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            ReCodeSort.x = 0;
            ReCodeSort.y = 0;
            ReCodeSort.a = 0;
            ReCodeSort.b = 0;
            Thread one = new Thread(() -> {
                //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                ReCodeSort.shortWait(10000);
                ReCodeSort.a = 1;
                ReCodeSort.x = ReCodeSort.b;
            });

            Thread other = new Thread(() -> {
                ReCodeSort.b = 1;
                ReCodeSort.y = ReCodeSort.a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + i + "次 (" + ReCodeSort.x + "," + ReCodeSort.y
                + "）";
            if (ReCodeSort.x == 0 && ReCodeSort.y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
