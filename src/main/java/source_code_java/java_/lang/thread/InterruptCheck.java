package source_code_java.java_.lang.thread;

/**
 * 演示线程中断过程
 *
 * 解释：
 * 我觉得这个例子主要涉及到以下几个问题：
 *
 * 1.对interrupt（）、isInterrupted()的理解。为什么BCD要运行呢？因为CPU在转线程又没有阻塞也没有转到其他语句，
 * 他当然要往下执行。我的理解是interrupt（）是抛出一个消息，“要求”调用他的进程中断，他只是负责发出要求，
 * 具体执行由其他语句实现。所以这个语句出现进程不会中断。
 *
 * 2. t.sleep(2000)之前，没有语句实现中断，所以这个状态（或者说这个“中断要求”）一直存在， t.sleep(2000)中响应了这个要求
 * （所以你看执行中间【没有】暂停2秒），所以这个状态（或者说这个“中断要求”）就没有了，在D里面就是false了。
 *
 * 3.对异常的理解。那么是怎么实现中断的效果呢？我的理解是通过异常。比如这个例子，发出中断信号过后，
 * 你调用t.sleep(2000)，这个t.sleep(2000)里面封装了一个东西，他收到了这个信号就向外抛出了一个InterruptedException ，交给try.catch，通过try.catch对异常的转接，【System.out.println("线程被中断了");】就被执行了。其实我觉得你这里不应该输出，而应该写sleep的，因为人家异常是要求你中断。
 *
 * 4.为什么到最后的D那段会显示线程又运行了？这个是对对语句执行的理解问题。方法执行的返回（或者你理解方
 * 法里执行的终止）仅限于以下情况：A.执行return语句；B.所有语句执行完毕；C.抛到方法外的异常（run()除外，因为异常不能抛到run()外面去），这里是有异常，但是他没有抛到外面去，所以要继续执行后面的。
 *
 * 总结一下吧，就是语句都是会被执行的，除非你把他的执行顺序改到其他方向去了，或者按照顺序语句执行终了。
 * 改顺序的措施包括return（方法里面的语句执行完毕）和通过异常处理选择。
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class InterruptCheck {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("A: isInterrupted="+t.isInterrupted());
        t.interrupt();
        System.out.println("B: isInterrupted="+t.isInterrupted());
        try {
            Thread.sleep(2000);
            System.out.println("线程没有被中断");
        }
        catch(InterruptedException x) {
            System.out.println("线程被中断了");
        }
        System.out.println("D: isInterrupted="+t.isInterrupted());
    }
}
