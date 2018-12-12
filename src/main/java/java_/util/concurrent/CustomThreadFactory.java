package java_.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 11:39
 **/
public class CustomThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger();

    public CustomThreadFactory(String featureOfGroup) {
        this.namePrefix = "CustomThreadFactory's " + featureOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        return thread;
    }
}
