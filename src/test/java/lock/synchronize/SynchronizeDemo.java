package test.java.lock.synchronize;

/**
 * @Desc 1 验证哪些类型的方法不在字节码中，private方法不在
 * 2）synchronized关键字 在字节码中的表达
 * @Author shuaijunhe
 * @CreateTime 2018/11/30 19:18
 **/
public class SynchronizeDemo {

    public void f(){
        int i = 1;
    }

    void f1(){
        int i = 1;
    }

    protected  void f2(){
        int i = 1;
    }

    /**
     * 此方法不在字节码中
     */
    private  void f3(){
        int i = 1;
    }

    public final  void f4(){
        int i = 1;
    }

    public static  void f5(){
        int i = 1;
    }

    /**
     * 此方法不在字节码中
     */
    private static  void f6(){
        int i = 1;
    }

    /**
     * public synchronized void test();
     */
    public synchronized void test(){
        int i = 2;
    }

    /**
     * 3: monitorenter
     * 4: iconst_2
     * 5: istore_2
     * 6: aload_1
     * 7: monitorexit
     */
    public void test1(){

        synchronized(this){
            int i = 2;
            ++i;
        }
    }
}
