package java_.lang.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author hsj
 * @create 2019/12/25
 */
public class TraceHander implements InvocationHandler {

    private Object target;

    public TraceHander(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(target + " 调用 " + method.getName() + ", 参数为：" + Arrays.toString(args));
        return method.invoke(target, args);
    }
}
