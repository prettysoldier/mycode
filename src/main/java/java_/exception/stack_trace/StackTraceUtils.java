package java_.exception.stack_trace;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author hsj
 * @create 2019/12/26
 */
public class StackTraceUtils {

    public  static  String getStackTrace(){
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < elements.length; i++){
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }

    public  static  String getStackTrace2(){
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        StackTraceElement[] elements = map.get(Thread.currentThread());
        StringBuilder sb = new StringBuilder();
        for(int i = 3; i < elements.length; i++){
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }
}
