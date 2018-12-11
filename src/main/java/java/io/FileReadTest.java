/**
 * 
 */
package java.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shuaijunhe
 *
 */
public class FileReadTest {


    String generalFile = "/test/iodemo.txt";

    public void generalFileRead(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("readGeneralFile");

            FileInputStream in = new FileInputStream(fileName);

            int i = in.read();

            while (i != -1) {

                i = in.read();

            }
            in.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void randomFileRead(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("readRandomFile");

            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            String str;
            while ((str = file.readLine()) != null) {

            }
            file.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void bufferedReader4FileReader(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("bufferedReader4FileReader");

            FileReader fr = new FileReader(fileName);

            BufferedReader bf = new BufferedReader(fr);
            String str;
            while ((str = bf.readLine()) != null) {


            }

            fr.close();
            bf.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void bufferedReader4InputStream(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("bufferedReader4InputStream");

            BufferedReader bf = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)));

            String str;
            while ((str = bf.readLine()) != null) {
//                System.out.println(str);

            }
            bf.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void bufferedReader4InputStream2(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("bufferedReader4InputStream2");

            BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(fileName));

            BufferedReader bf = new BufferedReader(new InputStreamReader(in));

            String str;
            while ((str = bf.readLine()) != null) {

            }
            in.close();
            bf.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void mappedByteBufferRead(String fileName) {
        long start = System.currentTimeMillis();
        try {

            System.out.print("readMappedFile");

            FileInputStream in = new FileInputStream(fileName);

            FileChannel channel = in.getChannel();

            int length = (int) channel.size();

            MappedByteBuffer buffer = channel.map(

                FileChannel.MapMode.READ_ONLY, 0, length);
//            ByteArrayBuilder bab = new ByteArrayBuilder();
            while (buffer.remaining() > 0) {
                byte b = buffer.get();
//                if (b == 10) {
//                    System.out.println(new String(bab.toByteArray()));
//                    bab = new ByteArrayBuilder();
//                } else {
//                    bab.append(b);
//                }

            }
            in.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        countTime(start);
    }

    public void countTime(long start) {

        System.out.println(":" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void main(String[] args) {
        FileReadTest test = new FileReadTest();
//        test.mappedByteBufferRead(test.generalFile);
//        test.bufferedReader4InputStream2(test.generalFile);
        test.bufferedReader4InputStream(test.generalFile);
//        test.bufferedReader4FileReader(test.generalFile);
//        test.generalFileRead(test.generalFile);
//        test.randomFileRead(test.generalFile);


    }

}