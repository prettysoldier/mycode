package java_.util.concurrent.executors;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.Thread构造器中的 stackSize 的作用？
 *    1）stackSize 如果VM支持这个参数：超过VM的最大值，按最大值；如果低于VM最小值，按最小值算。
 *              如果VM不支持这个参数，会忽略。
 *    2）如果没有特殊的需求，尽量不要修改此参数，因为它本身的作用和范围取决于平台，在不同的VM上使用此参数，
 *    跨平台迁移时，如果以前已设定了对应值，需要检查是否需要修改这个参数。
 *    3）参考 JVMStackTest
 *
 *
 *
 * @author shuaijunhe
 * @create 2019/10/25
 * @description
 */
public class MyThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    private static MyThreadFactory myThreadFactory = new MyThreadFactory();

    private MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    public static MyThreadFactory getMyThreadFactory() {
        return myThreadFactory;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                 -1);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }


}


