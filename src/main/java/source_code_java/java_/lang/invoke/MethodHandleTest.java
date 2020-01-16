package source_code_java.java_.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author heshuaijun
 * @date 2020/1/13 22:40
 */
public class MethodHandleTest {

    static class A{

        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main (String[] args) throws Throwable{

        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new A();
        getPrintlnMethodHandle(obj).invokeExact("hahah");
    }

    private static MethodHandle getPrintlnMethodHandle(Object obj) throws Throwable{
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(obj.getClass(), "println", methodType);
    }
}
