package java_.util.concurrent.locks;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
 * 如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
 * 如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。
 *
 * @author Shuaijun He
 */
public class MyBlockQueue {

    private List<Object> list = new ArrayList<>();

    public synchronized Object pop() throws InterruptedException {

        while (this.list.size() == 0) {
            this.wait();
        }
        if (this.list.size() > 0) {
            return this.list.remove(0);
        } else {
            return null;
        }
    }

    public synchronized void put(Object obj) {
        this.list.add(obj);
        this.notify();
    }

    public int size() {
        return this.list.size();
    }

    public static void main(String[] args) {
        MyBlockQueue bq = new MyBlockQueue();
        new Thread(() -> {
            while (true) {

                try {
                    System.out.println("取出值：" + bq.pop());
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            int i = 1;
            while (true) {
                bq.put(new Item(i + ""));
                System.out.println("存入值：" + i + ", 总数：" + bq.size());
                i++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}

class Item {
    private String name;

    /**
     * @param name
     */
    public Item(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }
}
