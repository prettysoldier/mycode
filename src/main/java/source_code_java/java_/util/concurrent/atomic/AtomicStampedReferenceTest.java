package source_code_java.java_.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 关心引用变量更改了几次
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class AtomicStampedReferenceTest {

    public static void main(String[] args) {

        // 创建AtomicStampedReference对象，持有Foo对象的引用，初始为null，版本为0
        AtomicStampedReference<A>  asr = new AtomicStampedReference<>(null,0);

        int[] stamp=new  int[1];
        // 调用get方法获取引用对象和对应的版本号
        A  oldRef = asr.get(stamp);
        // stamp[0]保存版本号
        int oldStamp=stamp[0];

        System.out.println(asr.getStamp() + ":" + asr.getReference());
        //尝试以CAS方式更新引用对象，并将版本号+1
        asr.compareAndSet(oldRef, new A(), oldStamp, oldStamp + 1);

        System.out.println(asr.getStamp() + ":" + asr.getReference());
    }


    static class A {

    }
}

