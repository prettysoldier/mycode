package test;

import java.util.HashMap;
import java.util.Map;


public class A<T> {

    public void f(T o){
        System.out.println(o);
    }
    
    public static void main(String[] args){
//        String[] ss = new String[12];
//        String s = new String("sdf");
//        Object o = new Object();
//        new A<Object>().f(ss);
//        new A<Object>().f(o);
        
        String name = extractMethodName();
        System.out.println(name);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("q", "sfg");
        map.put("a", new Object());
        map.put("asd", new String[]{"sdfgsdfg", "sdfgfsdg"});
//        JSONObject jsonObject = JSONObject.fromMap(productMap);  
        System.out.println(map);
    }
    
    private static String extractMethodName() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        boolean found = false;
        for (StackTraceElement element : stack) {
            final String className = element.getClassName();
            if (found) {
                if (!A.class.equals(className)) {
                    return className + '.' + element.getMethodName();
                }
            } else {
                found = "test.A".equals(className);
            }
        }
        return "-";
    }
}
