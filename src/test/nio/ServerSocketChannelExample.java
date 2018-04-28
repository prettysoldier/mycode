/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Shuaijun He
 */
public class ServerSocketChannelExample {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            if (socketChannel != null) {
                System.out.println("do something with socketChannel...");
            }
        }
    }
}
