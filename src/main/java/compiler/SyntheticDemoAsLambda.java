package compiler;

/**
 * lambda表达式与synthetic 无关
 * @author hsj
 * @create 2019/12/12
 */
 class SyntheticDemoAsLambda {

    public static void main(String[] args) {
        new Thread(()->{}).start();
    }

}
