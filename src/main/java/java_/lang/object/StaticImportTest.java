package java_.lang.object;

import static java.util.Objects.compare;
/**
 * 静态导入：可以导入某个特定的静态方法吗？可以。
 * @author heshuaijun
 * @date 2019/11/27 21:55
 */
public class StaticImportTest {

    public static void main (String[] args) {
        compare("1", "2", null);

    }
}
