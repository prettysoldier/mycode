package java.extend;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/30 18:21
 **/
public interface Interface1 {
    void i1();

    default void f(){
        A.a(this);
    }
}
class A{
    static void a(Interface1 i1){}
}