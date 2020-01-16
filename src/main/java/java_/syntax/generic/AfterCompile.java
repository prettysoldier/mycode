package java_.syntax.generic;

/**
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
        return t;
    }
}
