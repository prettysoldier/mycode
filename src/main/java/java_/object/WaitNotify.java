package java_.object;

@Deprecated
public class WaitNotify implements Runnable {

    /**
     * 顺序打印ABC
     * 不懂！此方法不可行！
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        WaitNotify worker_a = new WaitNotify("A", c, a);
        WaitNotify worker_b = new WaitNotify("B", a, b);
        WaitNotify worker_c = new WaitNotify("C", b, c);

        new Thread(worker_a).start();
        Thread.sleep(100);
        new Thread(worker_b).start();
        Thread.sleep(100);
        new Thread(worker_c).start();
        Thread.sleep(100);
    }

    private String name;
    private Object prev;
    private Object self;

    private WaitNotify(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (true) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name + " ");
                    if(name.equals("C")){
                        System.out.println();
                    }
                    count--;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    self.notify();//唤醒其他线程
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
