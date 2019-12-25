package compiler.synthetic;

/**
 * 匿名内部类与synthetic 无关
 * @author hsj
 * @create 2019/12/12
 */
public  class SyntheticDemoAsAnonymousInnerClass {

    public static void main(String[] args) {
        int a = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
            int b = 2;

        }).start();
    }

}
