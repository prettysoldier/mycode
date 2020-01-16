package source_code_java.java_.util.concurrent.sync;

import java.util.concurrent.Phaser;

/**
 * Phaser分层示例
 * @author shuaijunhe
 * @create 2019/10/21
 * @description
 */
public class Phaser_DemoForTiering {

    /**
     * 每个Phaser对象对应的工作线程（任务）数
     */
    private static final int TASKS_PER_PHASER = 4;

    public static void main(String[] args) {

        // 指定任务最多执行的次数
        int repeats = 3;
        Phaser root = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties + "] ---------------");
                return phase + 1 >= repeats || registeredParties == 0;
            }
        };

        Task[] taskers = new Task[10];
        // 根据任务数,为每个任务分配Phaser对象
        build(taskers, 0, taskers.length, root);

        for (int i = 0; i < taskers.length; i++) {
            new Thread(taskers[i]).start();
        }
    }


    private static void build(Task[] taskers, int lo, int hi, Phaser phaser) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(taskers, i, j, new Phaser(phaser));
            }
        } else {
            for (int i = lo; i < hi; ++i) {
                taskers[i] = new Task(phaser);
            }
        }

    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {
            //只要Phaser没有终止, 各个线程的任务就会一直执行
            while (!phaser.isTerminated()) {
                // 等待其它参与者线程到达
                int i = phaser.arriveAndAwaitAdvance();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": 执行完任务");
            }
        }
    }
}
