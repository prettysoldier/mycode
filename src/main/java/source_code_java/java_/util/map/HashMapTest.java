package source_code_java.java_.util.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(8);
        System.out.println(map);
        map.put("k", "v");
        map.put(null, null);
        map.put(null, null);
        map.put("a1", "12");
        map.put("d", "1");
        map.put("ad", "sd");


        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
