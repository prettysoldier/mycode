package source_code_java.java_.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 有时候，我们并不关心引用变量更改了几次，只是单纯的关心是否更改过，所以就有了AtomicMarkableReference：
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class AtomicMarkableReferenceTest {

    public static void main(String[] args) {

        AtomicMarkableReference<Integer> amr = new AtomicMarkableReference<>(1, false);

        boolean[] mark = new boolean[1];
        System.out.println(amr.get(mark) + ": " + mark[0]);
        amr.set(2, true);
        System.out.println(amr.get(mark) + ": " + amr.isMarked());
    }
}
