package java_.lang.instrument;



import java_.sun.misc.unsafe.MyUnsafe;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * javaagent 的全部用法？
 * 使用 javaagent 需要几个步骤：
 *
 * 1.定义一个 MANIFEST.MF 文件，必须包含 Premain-Class 选项，通常也会加入Can-Redefine-Classes 和 Can-Retransform-Classes 选项。
 *      Can-Redefine-Classes：？
 *      Can-Retransform-Classes： ？
 * 2.创建一个Premain-Class 指定的类，类中包含 premain 方法，方法逻辑由用户自己确定。
 * 3.将 premain 的类和 MANIFEST.MF 文件打成 jar 包。
 * 4.使用参数 -javaagent:/jar包路径=[agentArgs 参数] 启动要代理的方法。比如 MyUnsafe
 *      -javaagent:E:\data\idea_workspace\mycode\out\artifacts\PreMainTraceAgent\PreMainTraceAgent.jar=asdf
 *
 * 在执行以上步骤后，JVM 会先执行 premain 方法，大部分类加载都会通过该方法，注意：是大部分，不是所有。
 * 遗漏的主要是系统类，因为很多系统类先于 agent 执行，而用户类的加载肯定是会被拦截的。
 * 也就是说，这个方法是在 main 方法启动前拦截大部分类的加载活动，注意：是类加载之前。也就是说，我们可以在这个缝隙中做很多文章，比如修改字节码。
 *
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class PreMainTraceAgent {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP){

        inst = instP;

//        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {

//                System.out.println("premain load Class     :" + className);
                return classfileBuffer;
            }
        }, true);
    }

    public static void main (String[] args) {
        /**
         * 添加vm参数： -javaagent:E:\data\idea_workspace\mycode\out\artifacts\PreMainTraceAgent\PreMainTraceAgent.jar=asdf
         */
        System.out.println("通过Instrumentation获得对象A的内存：" + PreMainTraceAgent.sizeOf(new MyUnsafe.A()));
    }

    /**
     * 只是计算大约值
     * @param obj
     * @return
     */
    public static long sizeOf(Object obj){
        return inst.getObjectSize(obj);
    }
}
