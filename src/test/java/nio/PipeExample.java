/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Shuaijun He
 */
public class PipeExample {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..."
            + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();

        ByteBuffer readBuf = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(readBuf);
        

        readBuf.flip();

        while (readBuf.hasRemaining()) {
            System.out.print((char) readBuf.get());
        }
    }
}
