package source_code_java.java_.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author phenix
 * @date 2020/2/22 19:52
 */
public class AioDemo {

    private static final Object waitObject = new Object();

    public static void main (String[] args) {

        try {
            // 这个线程池是用来得到操作系统的“IO事件通知”的
            ExecutorService executorService = Executors.newFixedThreadPool(25);
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executorService);
            // 您也可以不使用线程池(不推荐)，如果决定不使用线程池，直接AsynchronousServerSocketChannel.open()就行了。
            AsynchronousServerSocketChannel server =
                    AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(8080));

            server.accept(server, new AioAcceptHandler());

            synchronized (waitObject){
                waitObject.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
