package source_code_java.java_.util.concurrent.sync;

import java.util.concurrent.Phaser;

/**
 * Phaser jdk1.7 用于分阶段任务处理，类似于“多阶段栅栏”
 * 多阶段栅栏，可以在初始时设定参与线程数，也可以中途注册/注销参与者，当到达的参与者数量满足栅栏设定的数量后，会进行阶段升级（advance）
 * 用LockSupport 、CAS/自旋 实现
 *
 * 概念：
 * 阶段 phase ：在任意时间点，Phaser只处于某一个phase(阶段)，初始阶段为0，最大达到Integerr.MAX_VALUE，然后再次归零。当所有parties参与者都到达后，phase值会递增。
 * 参与者 parties : parties(参与者)其实就是参与线程。CyclicBarrier中的参与者在初始构造指定后就不能变更，而Phaser既可以在初始构造时指定参与者的数量，
 *      也可以中途通过register、bulkRegister、arriveAndDeregister等方法注册/注销参与者。
 * arrive(到达) ：参与者的初始状态是unarrived的，当参与者到达（arrive）当前阶段（phase）后，状态就会变成arrived。
 * advance(进阶) ：当前参与者到达的数量等于注册的数量时，阶段就会发生进阶，也就是phase+1
 * termination(终止)：代表phaser到达终止状态
 * tiering(分层)：一种树状结构，通过构造的时候指定父节点。作用：当一个Phaser有大量的参与者的时候，内部的同步操作性能会急剧下降，而分层可以降低竞争，
 *          从而减少因同步导致的额外开销。
 *      在一个分层Phasers的树结构中，注册和撤销子Phaser或父Phaser是自动被管理的。当一个Phaser的参与者（parties）数量变成0时，
 *          如果有该Phaser有父结点，就会将它从父结点中溢移除。
 *
 * 同步状态state定义：
 * 0-15 (16bit) unarrived 未到达的参与者数目
 * 16-31 (16bit) parties 总参与者
 * 32-62 (31bit) 当前阶段
 * 63 (1bit) terminated 终止标志
 * 初始时 state = EMPTY = 1，表示此phaser还没有现成注册过。
 *      为什么不使用0 ？
 *
 * 注意：当前构造 new Phaser(parent, 0) 时，并不会立即在父节点中注册参与者，当此phaser第一次添加参与者时，才会向父节点注册。也就是延迟注册！
 *
 * @author shuaijunhe
 * @create 2019/10/21
 * @description
 */
public class Phaser_Demo {
    public static void main(String[] args) throws Exception{

        // 指定任务最多执行的次数
        int repeats = 3;

        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties + "] ---------------");
                return phase + 1 >= repeats  || registeredParties == 0;
            }
        };

        for (int i = 0; i < 3; i++) {
            phaser.register();                      // 注册各个参与者线程
            new Thread(new Task(phaser), "Thread-" + i).start();
        }
    }
    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            //只要Phaser没有终止, 各个线程的任务就会一直执行
            while (!phaser.isTerminated()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 等待其它参与者线程到达
                int i = phaser.arriveAndAwaitAdvance();
                // do something
                System.out.println(Thread.currentThread().getName() + ": 执行完任务");
            }
        }
    }
}
