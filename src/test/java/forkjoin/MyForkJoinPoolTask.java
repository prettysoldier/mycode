
package test.java.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuaijun He
 */
public class MyForkJoinPoolTask {

    public static void main(String[] args) throws InterruptedException {
        PrintTask task = new PrintTask(0, 300);
        //创建实例，并执行分割任务
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(task);
        //线程阻塞，等待所有任务完成
        pool.awaitTermination(3, TimeUnit.SECONDS);
        pool.shutdown();

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}

class PrintTask extends RecursiveAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 50; //最多只能打印50个数
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        if (this.end - this.start < PrintTask.THRESHOLD) {
            for (int i = this.start; i < this.end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值："
                    + i);
            }
        } else {
            int middle = (this.start + this.end) / 2;
            PrintTask left = new PrintTask(this.start, middle);
            PrintTask right = new PrintTask(middle, this.end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }

    }

}
