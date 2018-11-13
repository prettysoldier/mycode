package test.java;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map map  = new HashMap(12);
        System.out.println(map);
        map.put("k", "v");
    }
}
