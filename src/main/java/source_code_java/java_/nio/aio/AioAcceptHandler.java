package source_code_java.java_.nio.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * aio接受回调实现类
 * @author phenix
 * @date 2020/2/22 20:08
 */
public class AioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    private static Logger LOGGER = LoggerFactory.getLogger(AioAcceptHandler.class);

    @Override
    public void completed (AsynchronousSocketChannel socket, AsynchronousServerSocketChannel attachment) {
        System.out.println("AioAcceptHandler，completed");
        try {
            System.out.println("有客户端连接：" + socket.getRemoteAddress().toString());
            // 为了使AsynchronousServerSocketChannel（监听的socket）继续接受新连接的请求。
            attachment.accept(attachment, this);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socket.read(ByteBuffer.allocate(1024), new StringBuffer(), new AioReadHandler(socket, byteBuffer));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed (Throwable exc, AsynchronousServerSocketChannel attachment) {
        AioAcceptHandler.LOGGER.info("failed(Throwable exc, ByteBuffer attachment)");
    }

}