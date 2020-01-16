package source_code_java.java_.lang.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 13:53
 **/
public class SoftReferenceWithQueueTest {
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
