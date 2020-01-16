package source_code_java.java_.lang.finalize;

/**
 * @author hsj
 * @create 2020/1/6
 */
public class A {

    static A a = null;

    void isAlive(){

        System.out.println("yes");
    }

    @Override
    public void finalize() throws Throwable{
        a = this;
    }

    public static void main(String[] args) throws Exception{

        a = new A();
        a = null;
        System.gc();
        Thread.sleep(500);
        if(a != null){
            a.isAlive();
        } else {
            System.out.println("no");
        }
    }


}
