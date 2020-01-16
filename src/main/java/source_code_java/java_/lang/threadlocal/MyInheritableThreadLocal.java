
package source_code_java.java_.lang.threadlocal;

/**
 * InheritableThreadLocal 子线程可以继承父线程的值
 * 覆写了childValue(T parentValue) getMap(Thread t) createMap(Thread t, T firstValue) 三个方法
 * @author Shuaijun He
 */
public class MyInheritableThreadLocal extends InheritableThreadLocal<Object> {

    @Override
    protected Object initialValue() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception{
        Tools.myInheritableThreadLocal.get();
        ThreadA child = new ThreadA();
        child.start();

        Thread.sleep(1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(" main线程 :" + Tools.myInheritableThreadLocal.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        ThreadA th2 = new ThreadA();
//        th2.start();

    }
}

class Tools {
    public static MyInheritableThreadLocal myInheritableThreadLocal = new MyInheritableThreadLocal();
//    public static ThreadLocal<Object> myInheritableThreadLocal = new ThreadLocal<>();

}

class ThreadA extends Thread {
    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("threadA :" + Tools.myInheritableThreadLocal.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
