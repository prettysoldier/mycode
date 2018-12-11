/**
 * 
 */
package java.nio.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shuaijunhe
 *
 */
public class IODemo {
    private static int lineSize = 20_0000;//一万行
    private static int lineNum = 30;
    private static int pageSize = 5000;
    private static int sqlTime = 100;

    public static void main(String[] args) throws IOException {

        String path = "D:/test/iodemo.txt";
//        initData(path);
        IODemo.oldIO(path);
        IODemo.oldIO2(path);
    }

    @SuppressWarnings("unused")
    private static void initData(String path) throws IOException {
        for (int i = 0; i < lineSize; i++) {
            String newData = RandomUtil.getRandom(lineNum) + "\n";

            ByteBuffer buf = ByteBuffer.allocate(lineNum + 2);
            buf.put(newData.getBytes());
            buf.flip();
            try (RandomAccessFile aFile = new RandomAccessFile(path, "rw");
                    FileChannel channel = aFile.getChannel()) {
                channel.position(channel.size());
                while (buf.hasRemaining()) {
                    channel.write(buf);
                    channel.force(true);
                }
            }
        }
    }

    private static void oldIO(String path) {
        long start = System.currentTimeMillis();
        int count;
        try {
            count = FileUtil.getTotalLines(path);
            long page = (count % pageSize) == 0 ? count / pageSize
                : (count / pageSize) + 1;
            int startLine = 0;
            int endLine = 0;
            for (int i = 0; i < page; i++) {
                startLine = i * pageSize;
                endLine = (i + 1) * pageSize;
                if (endLine > count) {
                    endLine = count + 1;
                }
                FileUtil.readerFiletoList(path, startLine, endLine, pageSize);
                // 模拟批量入库
                Thread.sleep(sqlTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out
            .println("oldIO 耗时 " + (System.currentTimeMillis() - start) + "毫秒");
    }

    private static void oldIO2(String path) {
        long start = System.currentTimeMillis();
        try {
            BufferedReader br = null;
            List<String> list = new ArrayList<String>(pageSize);

            try {
                br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), "UTF-8"));
                String line = null;
                int n = 1;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                    line = null;
                    if (n % pageSize == 0) {
//                        list.parallelStream().forEach(System.out::println);
                        System.out.println(list.get(list.size() - 1));
                        Thread.sleep(sqlTime);
                        list.clear();
                    }
                    n++;
                }
                Thread.sleep(sqlTime);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
            }
            // 模拟批量入库
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(
            "oldIO2 耗时 " + (System.currentTimeMillis() - start) + "毫秒");
    }

    @SuppressWarnings("unused")
    private static void newIO(String path) throws IOException {
        // TODO
    }
}
