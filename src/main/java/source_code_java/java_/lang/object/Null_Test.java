package source_code_java.java_.lang.object;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class Null_Test implements Cloneable{

    public static void main(String[] args) {

        // 所有null都指向同一个地址吗？
        // 输出true。看字节码发现null是用了 ACONST_NULL 指令 : null进栈。
        // JVM并没有为null指派一个具体的值。
        Object o1= null;
        Object o2 = null;
        System.out.println(o1 == o2);

        // clone必须实现Cloneable接口，并覆写Object的clone()方法，在方法中super.clone()
        // 调用Object中的native方法，此方法是protected的。克隆后的对象是个新的对象。
        Null_Test o3 = new Null_Test();
        Null_Test o4 = o3.clone();
        System.out.println(o3 == o4);
        System.out.println(o3.equals(o4));
    }

    @Override
    public Null_Test clone(){
        try {
            return (Null_Test)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
