package jvm;

import java.util.Map;

/**
 *
 * main方法启动，至少启动5个线程
 * 1.main
 * 2.Attach Listener
 * 3.Finalizer Thread
 * 4.Reference Handler Thread
 * 5.Signal Dispatcher
 *
 * TODO 为何 FinalizerThread 和 Reference Handler Thread 都无法进入断点？？
 * @author hsj
 * @create 2019/12/17
 */
public class MainStartThread {
    public static void main(String[] args) throws Exception {

        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        map.forEach((k, v)->{
            System.out.println(k.getName());
        });
        Thread.sleep(3600_000);
    }
}
