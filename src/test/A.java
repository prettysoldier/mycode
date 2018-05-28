package test;

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
