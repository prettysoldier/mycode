package test.java.throwab;

public class A<T> {

    public void f(T o){
        System.out.println(o);
    }
    
    public static void main(String[] args){
        String name = extractMethodName();
        System.out.println(name);
    }
    
    private static String extractMethodName() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        boolean found = false;
        for (StackTraceElement element : stack) {
            final String className = element.getClassName();
            if (found) {
                return className + '.' + element.getMethodName();
            } else {
                found = "test.java.throwab.A".equals(className);
            }
        }
        return "-";
    }
}
