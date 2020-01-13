package java_.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author heshuaijun
 * @date 2020/1/13 22:40
 */
public class MethodHandleTest2 {

    class GrandFather{
        void think(){
            System.out.println("grandfarher");
        }
    }
    class Father extends GrandFather{
        @Override
        void think(){
            System.out.println("father");
        }
    }
    class Son extends Father{
        @Override
        void think(){

            MethodType methodType = MethodType.methodType(void.class);
            try {
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "think", methodType,
                        getClass());
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args) {
        (new MethodHandleTest2().new Son()).think();
    }
}
