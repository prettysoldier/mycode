package source_code_java.java_.lang.thread;

/**
 * thread的各个状态
 * 
 * @author Shuaijun He
 */
public class Mythread extends Thread {

    public Mythread() {
        super();
        System.out.println("构造方法：" + this.getState());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        System.out.println("run方法：" + this.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Mythread mythread = new Mythread();
        System.out.println("1：" + mythread.getState());
        mythread.start();
        System.out.println("2：" + mythread.getState());
        Thread.sleep(50);
        System.out.println("3：" + mythread.getState());

    }
}
