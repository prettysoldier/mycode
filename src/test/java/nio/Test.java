
package test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuaijun He
 */
public class Test {

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile aFile = new RandomAccessFile("D:/qqkey", "rw");
                FileChannel inChannel = aFile.getChannel();) {

            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

//                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }

        }

    }
}
