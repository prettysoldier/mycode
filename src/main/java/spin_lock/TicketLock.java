package spin_lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ticket Lock 是为了解决 SimpleSpinLock 的公平性问题：
 *      类似于现实中银行柜台的排队叫号：锁拥有一个服务号，表示正在服务的线程，
 *      还有一个排队号；每个线程尝试获取锁之前先拿一个排队号，
 *      然后不断轮询锁的当前服务号是否是自己的排队号，如果是，则表示自己拥有了锁，不是则继续轮询。
 *      当线程释放锁时，将服务号加1，这样下一个线程看到这个变化，就退出自旋。
 *
 * 缺点：
 * Ticket Lock 虽然解决了公平性的问题，但是多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum ，每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
 *
 * 下面介绍的CLH锁和MCS锁都是为了解决这个问题的。
 *
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
