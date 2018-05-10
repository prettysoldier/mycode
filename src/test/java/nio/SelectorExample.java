/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Shuaijun He
 */
public class SelectorExample {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        // register 1
//        SocketAddress socketAddress = new InetSocketAddress(
//            "http://jenkov.com", 80);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = socketChannel.register(selector,
            SelectionKey.OP_CONNECT, "第1个通道");
        // register 2
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, "第2个通道");

        System.out.println(selectionKey.attachment());

        System.out.println(socketChannel.isRegistered());

        int interestSet = selectionKey.interestOps();

        boolean isInterestedInAccept = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;
        System.out.println(isInterestedInAccept + "," + isInterestedInConnect
            + "," + isInterestedInRead + "," + isInterestedInWrite);

        int readySet = selectionKey.readyOps();
        System.out.println(readySet + "," + selectionKey.isAcceptable() + ","
            + selectionKey.isConnectable() + "," + selectionKey.isReadable()
            + "," + selectionKey.isWritable());

        selectionKey.channel();
        selectionKey.selector();

        System.out.println(selector.select(1000));

        while (true) {
            selector.select();

            //可以遍历这个已选择的键集合来访问就绪的通道。如下：
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    System.out
                        .println("a connection was accepted by a ServerSocketChannel.");
                    try (SocketChannel accS = ((ServerSocketChannel) key
                        .channel()).accept()) {
                        if (socketChannel != null) {
                            System.out
                                .println("do something with socketChannel...");
                        }
                    }
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
