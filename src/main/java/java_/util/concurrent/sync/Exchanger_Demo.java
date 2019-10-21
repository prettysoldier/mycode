package java_.util.concurrent.sync;

import java.util.concurrent.Exchanger;

/**
 * Exchanger jdk1.5 用于交换数据
 * thread1到达栅栏后，首先观察有没有其他线程到达栅栏，如果没有就会等待；如果已经有其他线程到达了，就会以成对的方式交换信息。
 * 非常适合用于两个线程之间的数据交换！！
 *
 * Exchanger的单槽交换过程：
 * 1、首先到达的线程1会将slot指向自身的node节点，表示槽位已经被占用；
 *      按照spin->yield->block这种锁升级的顺序进行优化的等待，等不到配对线程就会进入阻塞。
 * 2、后到达的线程2，会将slot清空，并获取item中的数据返回，并将自身的数据存到match中，并唤醒parked指向的线程1
 * 3、先到达的线程1被唤醒，由于match不为空，会退出自旋，并将match的值返回。
 *
 * Exchanger的多槽位交换：
 * 什么时候会触发多槽位交换：
 * 在单槽交换中，同时出现了多个配对的线程竞争修改slot槽位，导致某个线程cas修改slot失败时，就会初始化arena多槽数组，
 * 一旦初始化后，后续的所有交换都会走arenaExchange方法
 * 多槽位交换过程 ？
 *
 *
 *
 *
 *
 *
 * @author shuaijunhe
 * @create 2019/10/21
 * @description
 */
public class Exchanger_Demo {

    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();
        new Thread(new Producer(exchanger), "生产者").start();
        new Thread(new Consumer(exchanger), "消费者").start();
    }

    static class Producer implements Runnable {
        private final Exchanger<Message> exchanger;

        public Producer(Exchanger<Message> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Message message = new Message(null);
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);

                    message.setV("生产" + i);
                    System.out.println(Thread.currentThread().getName() + ": 生产了数据[" + i + "]");

                    message = exchanger.exchange(message);

                    System.out.println(Thread.currentThread().getName() + ": 交换得到数据[" + String.valueOf(message.getV()) + "]");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Consumer implements Runnable {
        private final Exchanger<Message> exchanger;

        public Consumer(Exchanger<Message> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Message msg = new Message(null);
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    msg.setV("消费者反馈" + i);
                    msg = exchanger.exchange(msg);
                    System.out.println(Thread.currentThread().getName() + ": 消费了数据[" + msg.getV() + "]");
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class Message{
        private String v;

        public Message(String v) {
            this.v = v;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }
}
