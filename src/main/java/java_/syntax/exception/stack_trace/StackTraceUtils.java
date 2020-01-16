package java_.syntax.exception.stack_trace;

import java.io.*;
import java.util.Map;

/**
 * 打印堆栈信息的四种方法
 * - new Throwable().getStackTrace()
 * - new Throwable().printStackTrace(PrintStream)
 * - Thread.getAllStackTraces().get(Thread.currentThread())
 * - Thread.dumpStack()
 * - ExceptionUtils.getFullStackTrace(e)
 *
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

    public  static  String getStackTrace3() {
        Throwable t = new Throwable();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 下面两种方式都可以！
//        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        PrintStream writer = new PrintStream(out);
        t.printStackTrace(writer);
        // 两种flush方法都可以！
//        writer.flush();
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
