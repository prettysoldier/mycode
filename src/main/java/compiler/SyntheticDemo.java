package compiler;

/**
 * 关于Synthetic的示例
 * Synthetic 是由编译器生成的构造器、方法或域。
 * 作用是：实现一些java特有的（而非jvm级别）的语法。
 * @author hsj
 * @create 2019/12/12
 */
public  class SyntheticDemo {

    public static void main(String[] args) {

        InnerClass innerObject = new InnerClass();
        System.out.println("inner: " + innerObject.inner);
    }

    private static class InnerClass{

        private String inner = "我在内部";


    }


}
