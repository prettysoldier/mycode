package java_.lang.thread;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/5 20:00
 **/
public class ThreadUncaughtExceptionDemo {
    public static void main(String[] args) {

        new MyOutThread().start();
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Thread:" + this.getName());
            throw new RuntimeException("故意抛出的异常");
        }
    }

    private static class MyOutThread extends Thread{
        @Override
        public void run() {
            System.out.println("out thread before :" + this.getName());
            MyThread myThread = new MyThread();

            myThread.setUncaughtExceptionHandler((t, e) -> {
                System.out.println("Exception Thread:" + t.getName());
                System.out.println("Exception content:" + ExceptionUtils.getStackTrace(e));
            });
            myThread.start();
            System.out.println("out thread after");
        }
    }
}

