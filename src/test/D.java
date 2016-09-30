/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test;

/**
 * @author Shuaijun He
 */
public class D {

    public static void main(String[] args) {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(
//            new FileInputStream(new File("sdf"))))) {
//            br.readLine();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        try {
            int a = 0;
            System.out.println(10 / a);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
