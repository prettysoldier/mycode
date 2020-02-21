package source_code_java.java_.nio.mmap;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author phenix
 * @date 2020/2/21 20:56
 */
public class Writer {

    private static int count = 10*1024*1024; // 10 MB

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        RandomAccessFile memoryMappedFile = new RandomAccessFile("largeFile.txt", "rw");

        // Mapping a file into memory
        MappedByteBuffer out = memoryMappedFile.getChannel().map(
                FileChannel.MapMode.READ_WRITE, 0, count);

        // Writing into Memory Mapped File
        for (int i = 0; i < count; i++) {
            out.put((byte) i );
        }
        TimeUnit.HOURS.sleep(1);

        memoryMappedFile.close();
    }
}
