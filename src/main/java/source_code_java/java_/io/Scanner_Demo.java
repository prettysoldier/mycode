package source_code_java.java_.io;

import java.util.Scanner;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class Scanner_Demo {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)){
            while (true) {
                int i = scanner.nextInt();
                System.out.println(i);
            }
        }
    }
}
