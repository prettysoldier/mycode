package test.java.access.permission;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/23 16:39
 **/
public class A {
    private void a() {
    }

    protected  void b(){}

    public static void main(String[] args) {
        new A().a();
    }

}

