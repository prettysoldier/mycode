package test.java.lang.reference;

import java.lang.ref.SoftReference;

/**
 * @Desc 验证 ：如果内存没有达到OOM,SoftReference引用的对象会被回收吗?
 * 答案是不会
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 14:49
 **/
public class SoftReferenceWhenIdle {

    public static void main(String[] args) {
        House seller = new House();

        SoftReference buyer = new SoftReference(seller);
        seller = null;
        while(true){
            System.gc();
            System.runFinalization();

            if(buyer.get() == null){
                System.out.println("house is null.");
                break;
            }
            else{
                System.out.println("still there");
            }
        }
    }
    private static class House{
        private static final Integer DOOR_NUMBER = 2000;
        public Door[] doors = new Door[DOOR_NUMBER];
        class Door{}
    }

}
