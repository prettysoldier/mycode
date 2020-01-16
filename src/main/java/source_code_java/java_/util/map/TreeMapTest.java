package source_code_java.java_.util.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/4 17:16
 **/
public class TreeMapTest {
    public static void main(String[] args) {

        Map<Integer, Object> treeMap = new TreeMap<>();
        Object obj = new Object();
        treeMap.put(55, obj);
        treeMap.put(56, obj);
        treeMap.put(57, obj);
        treeMap.put(58, obj);
        treeMap.put(83, obj);
        treeMap.remove(57);
        treeMap.put(59, obj);
    }
}
