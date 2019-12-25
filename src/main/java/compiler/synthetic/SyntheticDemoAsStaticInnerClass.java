package compiler.synthetic;

/**
 * 关于Synthetic的示例
 * Synthetic 是由编译器生成的构造器、方法或域。
 * 作用是：实现一些java特有的（而非jvm级别）的语法。
 * @author hsj
 * @create 2019/12/12
 */
public  class SyntheticDemoAsStaticInnerClass {

    private static String outter = "外部";

    public static void main(String[] args) {

        // 如果注释掉下面两行，也就是不使用内部类，编译器是不会编译内部类的。
        InnerClass innerObject = new InnerClass();
        System.out.println("inner: " + innerObject.inner);
        System.out.println("inner: " + innerObject.getOutter());


    }

    private static class InnerClass{

        private String inner = "我在内部";

        private String getOutter(){
            return "getOutter : " + outter;
        }

    }


}
