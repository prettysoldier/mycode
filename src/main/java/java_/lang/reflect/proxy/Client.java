package java_.lang.reflect.proxy;

import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author hsj
 * @create 2019/12/25
 */
public class Client {

    public static void main(String[] args) {


//        demo1();
        demo2();

    }

    private static void demo1(){
        Object[] arr = new Object[1000];
        for(int i = 0; i < arr.length; i++){
            arr[i] = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, new TraceHander(i + 1));
        }

        int result = Arrays.binarySearch(arr, 3);
        if(result >= 0){
            System.out.println(arr[result]);
        }
    }

    /**
     * 多个代理
     */
    private static void demo2(){
        Object[] arr = new Object[1000];
        for(int i = 0; i < arr.length; i++){
            Object o = Proxy.newProxyInstance(null, new Class[]{Comparable.class, Serializable.class}, new TraceHander(i + 1));
            arr[i] = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, new TraceHander(o));
        }

        int result = Arrays.binarySearch(arr, 3);
        if(result >= 0){
            System.out.println(arr[result]);
        }
    }
}
