package source_code_java.java_.syntax.compiler.synthetic;

/**
 * lambda表达式与synthetic 无关
 * @author hsj
 * @create 2019/12/12
 */
 class SyntheticDemoAsLambda {

    public static void main(String[] args) {
        int a = 3;
        new Thread(()->{
            System.out.println(a);
        }).start();
    }

}
