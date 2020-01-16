package source_code_java.java_.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1.所有文件IO的相对路径的是由系统属性：user.dir决定的。
 * @author hsj
 * @create 2019/11/22
 */
public class FileInputStream_Demo {

    public static void main(String[] args) {

        // D:\idea_workspace\mycode
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

        try(InputStream in = new BufferedInputStream(new FileInputStream("/cmd.txt"));
            OutputStream out = new FileOutputStream("E:/out2.txt")){

            byte[] temp = new byte[1024];
            int b;
            while((b = in.read(temp)) != -1){
                out.write(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
