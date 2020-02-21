package source_code_java.java_.nio.mmap;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author phenix
 * @date 2020/2/21 20:50
 */
public class MmapTest {

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
        System.out.println("Writing to Memory Mapped File is completed");

        // reading from memory file in Java
        for (int i = 0; i < 10; i++) {
            System.out.print(out.get(i));
        }
        System.out.println("Reading from Memory Mapped File is completed");

        memoryMappedFile.close();
    }

}
