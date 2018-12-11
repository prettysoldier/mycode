package main.test.java.util.collection.list.arraylist;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/30 16:28
 **/
public class ArrayListSpliteratorTest {

    private static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    AtomicInteger count = new AtomicInteger(0);

    List<String> strList = createList();
    Spliterator spliterator = strList.spliterator();

    public static void main(String[] args) {
        new ArrayListSpliteratorTest().mytest();
    }
    /**
     * 多线程计算list中数值的和
     * 测试spliterator遍历
     */
    public void mytest(){
        for(int i=0;i<10;i++){
            new MyThread().start();
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果为：" + count);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程"+threadName+"开始运行-----");
            Spliterator split = spliterator.trySplit();
            // 注意一定要做非空判断
            if(split == null){
                System.out.println("已经无法再分割了，结束" + threadName);
                return;
            } else {
                Gson gson = new Gson();
                System.out.println(gson.toJson(split) +","+ threadName);
            }
            split.forEachRemaining(o -> {
                if(isInteger((String)o)){
                    int num = Integer.parseInt(o +"");
                    count.addAndGet(num);
                    System.out.println("---数值："+num+"------"+threadName);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("线程"+threadName+"运行结束-----");
        }
    }

    private static List<String> createList(){
        List<String> result = new ArrayList<>();
        for(int i=0; i < 100; i++){
            if(i % 10 == 0){
                result.add(i+"");
            }else{
                result.add("aaa");
            }
        }
        return result;
    }

    public static boolean isInteger(String str) {

        return pattern.matcher(str).matches();
    }
}
