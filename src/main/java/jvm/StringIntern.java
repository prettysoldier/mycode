package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试常量池的存储位置
 * 如果在jdk1.6环境下运行 同时限制方法区大小 将报OOM后面跟着PermGen space说明方法区OOM，即常量池在永久代
 * 如果是jdk1.7或1.8环境下运行 同时限制堆的大小  将报ava.lang.OutOfMemoryError: Java heap space。即常量池在堆中。
 *      -Xmx64m   报java.lang.OutOfMemoryError: Java heap space
 * @author heshuaijun
 * @date 2019/10/4 22:00
 */
public class StringIntern {
    //运行如下代码探究运行时常量池的位置
    public static void main(String[] args) throws Throwable {
        //用list保持着引用 防止full gc回收常量池
        List<String> list = new ArrayList<String>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}

