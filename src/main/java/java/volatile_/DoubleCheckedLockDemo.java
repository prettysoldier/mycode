package main.test.java.volatile_;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Desc 双重校验的问题: 没有复现 加不加volatile，结果一样
 * @Author shuaijunhe
 * @CreateTime 2018/12/7 14:03
 **/
public class DoubleCheckedLockDemo {

    public static void main(String[] args) {
        int n = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        executorService.execute(()->{
            SingleInstance instance = SingleInstance.getInstance();
            if(instance != null){
                System.out.println(Thread.currentThread().getName() + ": " + instance.getV());
            }
        });
        System.out.println("go on");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.execute(()->{
            System.out.println("inner");
            boolean isDone = false;
            while(!isDone) {
                System.out.print("...");
                SingleInstance instance = SingleInstance.instance;
                if (instance != null) {
                    System.out.println(Thread.currentThread().getName() + ": " + instance.getV());
                    if(10 == instance.getV()){
                        isDone = true;
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("inner over");
        });

        System.out.println("over");
    }




}

class SingleInstance{

    public static volatile SingleInstance instance = null;
    public static SingleInstance getInstance(){
        if(instance == null){
            synchronized (SingleInstance.class){
                if(instance == null){
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }

    private int v;

    private SingleInstance() {
        System.out.println(Thread.currentThread().getName() + "开始构建");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.v = 10;
        System.out.println(Thread.currentThread().getName() + "完成构建");
    }

    public int getV() {
        return v;
    }
}
