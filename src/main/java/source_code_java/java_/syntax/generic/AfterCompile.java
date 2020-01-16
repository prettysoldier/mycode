package source_code_java.java_.syntax.generic;

/**
 * 编译后 T 的类型变成了 Object
 * @author hsj
 * @create 2019/12/26
 */
public class AfterCompile<T> {

    T t;
    String s;

    void add(T t){
        this.t = t;
    }

    T get(){
        T t1 = t;
        return t;
    }
}
