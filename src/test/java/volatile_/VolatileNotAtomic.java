package test.java.volatile_;

/**
 * @Desc 为什么同步不好用呢？
 * 不是同步不好用，是&&的短路机制: while(substractThread.isAlive() && addThread.isAlive()){ }
 * 前面线程停止后，不会判断后面的线程
 * @Author shuaijunhe
 * @CreateTime 2018/12/7 14:44
 **/
public class VolatileNotAtomic {
    private static volatile long count = 0L;
    private static final int NUMBER = 100_0000;

    public static void main(String[] args) {
        Thread substractThread = new SubstractThread();
        Thread addThread = new AddThread();
        substractThread.start();
        addThread.start();

        while(substractThread.isAlive()){ }
        while(addThread.isAlive()){ }

        System.out.println("count 最后的值为：" + count);
    }

    private static class AddThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < NUMBER; i++){
                synchronized (VolatileNotAtomic.class) {
                    count++;
                }
            }
        }
    }

    private static class SubstractThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < NUMBER; i++){
                synchronized (VolatileNotAtomic.class) {
                    count--;
                }
            }
        }
    }
}
