package compiler;

import java.lang.reflect.Field;

/**
 * 匿名内部类与synthetic 无关
 * @author hsj
 * @create 2019/12/12
 */
 class SyntheticDemo3 {

    public static void main(String[] args) {
        new Thread(()->{}).start();
    }

}
