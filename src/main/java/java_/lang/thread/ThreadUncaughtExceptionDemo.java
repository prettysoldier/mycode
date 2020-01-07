package java_.lang.thread;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * run 方法不允许抛出已检查异常。
 * 如果抛出未检查异常，可以通过设置未捕获异常处理器进行处理：setUncaughtExceptionHandler
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

