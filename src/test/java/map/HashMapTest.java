package test.java.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>(1, 1);
        System.out.println(map);
        map.put("k", "v");
    }
}
