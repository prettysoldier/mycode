package source_code_java.java_.syntax.override;

/**
 * 1.覆写时，如果返回值不同，能编译通过吗？不能。
 * Error:(22, 16) java: java_.override.Son中的f()无法覆盖java_.override.Father中的f()
 *   返回类型int与java.lang.String不兼容
 * 2.覆写时子类方法不能降低超类方法的可见性。
 * Error:(31, 20) java: java_.override.Son中的f()无法覆盖java_.override.Father中的f()
 *   正在尝试分配更低的访问权限; 以前为package
 *
 * @author heshuaijun
 * @date 2019/11/29 18:03
 */
public class Override_Demo {

    public static void main (String[] args) {
        Son son = new Son();
        son.f();
    }
}

class Father{
    String f(){
        return "Father";
    }
}

class Son extends  Father{
//    public int f () {
//        return 1;
//    }

//    @Override
//    private String f () {
//        return super.f();
//    }
}
