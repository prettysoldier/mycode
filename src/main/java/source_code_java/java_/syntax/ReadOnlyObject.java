package source_code_java.java_.syntax;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/5 21:20
 **/
public class ReadOnlyObject {
    static Integer i = new Integer(1);

    public static void main(String[] args) {
        new MyThread().start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }


    private static class MyThread extends Thread{
        @Override
        public void run() {
            i = 3;
        }
    }
}
