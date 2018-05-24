package test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuaijun He
 */
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        String newData = "New String to write to file..."
            + System.currentTimeMillis() + "\n";

        ByteBuffer buf = ByteBuffer.allocate(50);
        buf.put(newData.getBytes());

        buf.flip();

        try (RandomAccessFile aFile = new RandomAccessFile("D:/tt.txt", "rw");
                FileChannel channel = aFile.getChannel()) {

            channel.position(channel.size());

            while (buf.hasRemaining()) {
                channel.write(buf);
                channel.force(true);
            }
        }

    }
}
