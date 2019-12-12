package compiler;

/**
 * 匿名内部类与synthetic 无关
 * @author hsj
 * @create 2019/12/12
 */
public  class SyntheticDemo2 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

}
