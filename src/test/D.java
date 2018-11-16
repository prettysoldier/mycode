
package test;

import java.util.Date;

/**
 * @author Shuaijun He
 */
@SuppressWarnings("ALL")
public class D {

    private boolean haha;

    public boolean isHaha() {
        return haha;
    }

    public void setHaha(boolean haha) {
        this.haha = haha;
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
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

        Integer c = 321;
        Integer b = 321;
        System.out.println(c == b);

        try {
            int a = 0;
            System.out.println(10 / a);
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        //预期大于3，结果是3
        System.out.println(ary.length);
    }
}
