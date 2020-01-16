package source_code_java.java_.lang.reference;

import java.lang.ref.WeakReference;

/**
 * @Desc 验证 ：如果内存没有达到OOM,WeakReference引用的对象会被回收吗?
 * 答案是会
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 14:49
 **/
public class WeakReferenceWhenIdle {

    public static void main(String[] args) {
        House seller = new House();

        WeakReference buyer = new WeakReference(seller);
        seller = null;
        while(true){


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
