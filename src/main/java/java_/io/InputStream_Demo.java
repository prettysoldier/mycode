package java_.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author heshuaijun
 * @date 2019/11/20 22:37
 */
public class InputStream_Demo {

    public static void main (String[] args) throws Exception {

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

        try(InputStream in = new BufferedInputStream(new FileInputStream("out.txt"))){

            System.out.println(in.read());
        }

        InputStreamReader isr = new InputStreamReader(System.in);
        while(true){
            int i = isr.read();
            char[] chars = Character.toChars(i);
            System.out.print(String.valueOf(chars));
        }
    }
}
