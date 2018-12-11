package java.util.map;

import java.util.WeakHashMap;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 15:36
 **/
public class WeakHashMapDemo {
    public static void main(String[] args) {
        House house1 = new House("1号房源");
        House house2 = new House("2号房源");
        WeakHashMap<House, Object> map = new WeakHashMap<>();
        map.put(house1, new Object());
        map.put(house2, new Object());
        System.out.println("before, size=" + map.size());
        System.out.println(map);
        house1 = null;
        System.gc();
        System.runFinalization();

        System.out.println("after, size=" + map.size());
        System.out.println(map);

    }

    private static class House{
        private String name;

        public House(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
