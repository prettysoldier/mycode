package java_.lang.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectsTest {
    public static void main(String[] args) {
        // 浅复制
        System.out.println(Objects.equals("asdf", "asdf"));
        // 由于AbstractList覆写了equals方法，比较每个元素是否相等，如果列表中都没有元素返回true。
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        System.out.println(Objects.deepEquals(l1, l2));
    }

}
