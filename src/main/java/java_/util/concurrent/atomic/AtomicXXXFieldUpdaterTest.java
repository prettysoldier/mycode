package java_.util.concurrent.atomic;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicXXXFieldUpdater可以以一种线程安全的方式操作非线程安全对象的某些字段。
 *
 * 上述未对Account做并发控制，最终账户金额很可能小于100。
 * 按照之前学习的atomic框架，可以将Account类的int类型字段改为AtomicInteger，或者在Task任务类中，将所有涉及到共享变量的地方都加锁访问。
 * 那么，还有没有其它解决方式？
 * AtomicIntegerFiledUpdater的引入，使得我们可以在不修改用户代码（调用方）的情况下，就能实现并发安全性。
 * 能用AtomicXXXFieldUpdater实现的并发控制，同步器和其它原子类都能实现，
 * 但是使用AtomicXXXFieldUpdater，符合面向对象设计的一个基本原则——开闭原则，尤其是对一些遗留代码的改造上。
 *
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class AtomicXXXFieldUpdaterTest {

    public static void main(String[] args) throws InterruptedException {
        // 初始金额0
        Account account = new Account(0);

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Task(account));
            list.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }
        System.out.println(account.toString());
    }


    private static class Task implements Runnable {
        private Account account;

        Task(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++){

                account.increMoney();
            }
        }
    }

    private static class Account {
        /**
         * Must be volatile type!!
         */
        private volatile int money;
        private AtomicIntegerFieldUpdater moneyUpdater = AtomicIntegerFieldUpdater.newUpdater(Account.class, "money");

        Account(int initial) {
            this.money = initial;
        }

        public void increMoney() {
//            money++;
            moneyUpdater.incrementAndGet(this);
        }

        public int getMoney() {
            return money;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "money=" + money +
                    '}';
        }
    }
}
