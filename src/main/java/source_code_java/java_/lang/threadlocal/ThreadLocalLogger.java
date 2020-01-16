package source_code_java.java_.lang.threadlocal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * 线程独有的日志打印器
 * @author shuaijunhe
 * @create 2019/10/29
 */
public class ThreadLocalLogger {

    private static final ThreadLocal<Log> tsLogCollection = new ThreadLocal<>();

    public static void println(String s) {
        getTSLog().println(s);
    }

    public static void close() {
        getTSLog().close();
    }

    private static Log getTSLog() {
        Log tsLog = tsLogCollection.get();
        if (tsLog == null) {
            tsLog = new Log(Thread.currentThread().getName() + "-log.txt");
            tsLogCollection.set(tsLog);
        }
        return tsLog;
    }
}

/**
 * 实际执行记录日志的类，每个线程都会拥有一个该类的实例
 *

 */
class Log {

    private PrintWriter writer = null;

    public Log(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String s) {
        writer.println(s);
    }

    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }

}

class ClientThread extends Thread {

    public ClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " BEGIN");
        for (int i = 0; i < 10; i++) {
            ThreadLocalLogger.println(getName() + " : i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        ThreadLocalLogger.close();
        System.out.println(getName() + " END");
    }

    public static void main(String[] args) {

        new ClientThread("Alice").start();
        new ClientThread("Bobby").start();
        new ClientThread("Chris").start();
    }
}