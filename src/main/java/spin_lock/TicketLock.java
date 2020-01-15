package spin_lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hsj
 * @create 2020/1/15
 */
public class TicketLock {

    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<>();

    /**
     * 公平锁，先到先得
     */
    public void lock(){
        int ticket = ticketNum.getAndIncrement();
        LOCAL.set(ticket);
        while(ticket != serviceNum.get()){}
    }

    public void unlock(){
        int ticket = LOCAL.get();
        // 不是自己上的锁，不能解锁
        serviceNum.compareAndSet(ticket, ticket + 1);
    }

}
