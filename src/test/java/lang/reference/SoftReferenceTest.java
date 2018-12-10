package test.java.lang.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc 虽然OOM前会清理大量的House对象，但是SoftRenference还是占用一定的空间，且被ArrayList强引用，最终会发生OOM
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 13:53
 **/
public class SoftReferenceTest {
    public static void main(String[] args) {
        List<SoftReference> houses = new ArrayList<>();
        int i = 0;
        while(true){
            try {
                // 为了使监控器jvisualvm正常工作
                if(i % 10 == 0){
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SoftReference<House> house = new SoftReference<>(new House());
            houses.add(house);
            System.out.println("i=" + (++i));
        }
    }
    private static class House{
        private static final Integer DOOR_NUMBER = 2000;
        public Door[] doors = new Door[DOOR_NUMBER];
        class Door{}
    }
}

