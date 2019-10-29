
package java_.util.concurrent.executors.fork_join;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 通过RecursiveTask的返回值，来对一个数组的元素进行累加
 *
 * @author Shuaijun He
 */
public class ForkJoinPoolForTaskDemo {

    static int ARR_LENGTH = 400_0000;

    public static void main(String[] args) throws Exception {
        int[] arr = new int[ForkJoinPoolForTaskDemo.ARR_LENGTH];
        Random random = new Random();
        //初始化100个数组元素
        for (int i = 0, len = arr.length; i < len; i++) {
            int temp = random.nextInt(10);
            //对数组元素赋值，并将数组元素的值添加到sum总和中
            arr[i] = temp;
        }
        int total = 0;
        long start = System.currentTimeMillis();
        for (int element : arr) {
            total += element;
        }
        long spent = System.currentTimeMillis() - start;
        System.out.println("初始化数组总和：" + total + ", spent: " + spent);

        long start1 = System.currentTimeMillis();
        SumTask task = new SumTask(arr, 0, arr.length);
//        创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> future = pool.submit(task); //提交分解的SumTask 任务
        int result = future.get();
        long spent1 = System.currentTimeMillis() - start1;
        System.out.println("多线程执行结果：" + result + ", spent: " + spent1);
        pool.shutdown(); //关闭线程池

    }

    /**
     * ClassName: SumTask <br/>
     * Function: 继承抽象类RecursiveTask，通过返回的结果，来实现数组的多线程分段累累加
     * RecursiveTask 具有返回值
     * date: 2017年12月4日 下午6:08:11 <br/>
     *
     * @author prd-lxw
     * @version 1.0
     * @since JDK 1.7
     */
    static class SumTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = -3020481591629633367L;

        private static final int THRESHOLD = ForkJoinPoolForTaskDemo.ARR_LENGTH / 8; //每个小任务 最多只累加20个数
        private int arry[];
        private int start;
        private int end;

        /**
         * Creates a new instance of SumTask.
         * 累加从start到end的arry数组
         *
         * @param arry
         * @param start
         * @param end
         */
        public SumTask(int[] arry, int start, int end) {
            super();
            this.arry = arry;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //当end与start之间的差小于threshold时，开始进行实际的累加
            if (this.end - this.start < SumTask.THRESHOLD) {
                for (int i = this.start; i < this.end; i++) {
                    sum += this.arry[i];
                }
                return sum;
            } else {//当end与start之间的差大于threshold，即要累加的数超过20个时候，将大任务分解成小任务
                int middle = (this.start + this.end) / 2;
                SumTask left = new SumTask(this.arry, this.start, middle);
                SumTask right = new SumTask(this.arry, middle, this.end);
                //并行执行两个 小任务
                left.fork();
                right.fork();
                //把两个小任务累加的结果合并起来
                return left.join() + right.join();
            }

        }
    }
}
