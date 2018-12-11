package main.test.java.lang.threadlocal;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Desc TODO 出现问题，无法调试,多线程情况下断点没有截住
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 20:21
 **/
public class TraceDemo {


    private static final ThreadLocal<FullLinkContext> FULL_LINK_THREADLOCAL = new ThreadLocal();
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();// 这样写有问题

    public static FullLinkContext getContext(){
        FullLinkContext context = FULL_LINK_THREADLOCAL.get();
        if(context == null){
            FULL_LINK_THREADLOCAL.set(new FullLinkContext());
            context = FULL_LINK_THREADLOCAL.get();
        }
        System.out.println(context);
        return context;
    }
    /**
     * 全链路上下文
     */
    public static class FullLinkContext{
        private String traceId;


        public String getTraceId() {
            if(StringUtils.isEmpty(this.traceId)){
                int i = ThreadLocalRandom.current().nextInt(1000);
//                int i = RANDOM.nextInt(1000);
                System.out.println(Thread.currentThread().getName() + ":" + i);
                this.traceId = "init_traceid_" + i;
            }
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 9; i++){
            new Thread(()->{
                String traceId = TraceDemo.getContext().getTraceId();
                System.out.println(Thread.currentThread().getName() + " : " + traceId);

            }).start();
        }

    }

}
