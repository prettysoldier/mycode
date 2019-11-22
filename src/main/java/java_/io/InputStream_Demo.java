package java_.io;

import java.io.InputStreamReader;

/**
 * @author heshuaijun
 * @date 2019/11/20 22:37
 */
public class InputStream_Demo {

    public static void main (String[] args) throws Exception {



        InputStreamReader isr = new InputStreamReader(System.in);
        while(true){
            int i = isr.read();
            char[] chars = Character.toChars(i);
            System.out.print(String.valueOf(chars));
        }
    }
}
