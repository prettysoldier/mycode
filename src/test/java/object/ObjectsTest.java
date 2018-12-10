package test.java.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectsTest {
    public static void main(String[] args) {
        // 浅复制
        System.out.println(Objects.equals("asdf", "asdf"));
        // 深复制
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        System.out.println(Objects.deepEquals(l1, l2));
    }

}
