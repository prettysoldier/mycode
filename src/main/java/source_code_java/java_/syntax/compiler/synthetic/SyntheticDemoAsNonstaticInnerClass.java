package source_code_java.java_.syntax.compiler.synthetic;

/**
 * 非静态内部类
 *
 * 在SyntheticDemoAsNonstaticInnerClass$InnerClass.class中会生成一个构造器和
 * @author hsj
 * @create 2019/12/12
 */
 class SyntheticDemoAsNonstaticInnerClass {

     private String outter = "外部";

    public static void main(String[] args) {

        SyntheticDemoAsNonstaticInnerClass.InnerClass innerObject = new SyntheticDemoAsNonstaticInnerClass().new InnerClass();
        System.out.println("inner: " + innerObject.inner);
        System.out.println("getOutter: " + innerObject.getOutter());

    }

    private class InnerClass{

        private String inner = "我在内部";

        private String getOutter(){
            return "getOutter : " + SyntheticDemoAsNonstaticInnerClass.this.outter;
        }

    }

}

