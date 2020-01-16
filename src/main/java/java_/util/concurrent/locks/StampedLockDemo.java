package java_.util.concurrent.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock类，在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，
 * 该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制并发。

 * 先来看下，为什么有了ReentrantReadWriteLock，还要引入StampedLock？
 * 读写锁如果使用不当，很容易产生“饥饿”问题：
 * 比如在读线程非常多，写线程很少的情况下，很容易导致写线程“饥饿”，虽然使用“公平”策略可以一定程度上缓解这个问题，
 * 但是“公平”策略是以牺牲系统吞吐量为代价的。
 *
 * StampedLock的使用：
 * 所有获取锁的方法，都返回一个邮戳（Stamp），Stamp为0表示获取失败，其余都表示成功；
 * 所有释放锁的方法，都需要一个邮戳（Stamp），这个Stamp必须是和成功获取锁时得到的Stamp一致； *
 * StampedLock有三种访问模式：
 *      ①Reading（读模式）：功能和ReentrantReadWriteLock的读锁类似
 *      ②Writing（写模式）：功能和ReentrantReadWriteLock的写锁类似
 *      ③Optimistic reading（乐观读模式）：这是一种优化的读模式。
 *
 * 注意问题：
 * 1）StampedLock是 不可重入的：如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁！！！
 * 2）StampedLock支持读锁和写锁的相互转换
 *      我们知道RRW中，当线程获取到写锁后，可以降级为读锁，但是读锁是不能直接升级为写锁的。
 *      StampedLock提供了读锁和写锁相互转换的功能，使得该类支持更多的应用场景。
 * 3）无论写锁还是读锁，都不支持Conditon等待
 *
 * 源码：
 * StampedLock内部定义了很多常量，定义这些常量的根本目的还是和ReentrantReadWriteLock一样，对同步状态值按位切分，以通过位运算对State进行操作：
 * 对于StampedLock来说，写锁被占用的标志是第8位为1，读锁使用0-7位，正常情况下读锁数目为1-126，超过126时，
 * 使用一个名为readerOverflow的int整型保存超出数。
 *
 * StampedLock的等待队列与RRW的CLH队列相比，有以下特点：
 * 1.当入队一个线程时，如果队尾是读结点，不会直接链接到队尾，而是链接到该读结点的cowait链中，cowait链本质是一个栈；
 * 2.当入队一个线程时，如果队尾是写结点，则直接链接到队尾；
 * 3.唤醒线程的规则和AQS类似，都是首先唤醒队首结点。区别是StampedLock中，当唤醒的结点是读结点时，
 * 会唤醒该读结点的cowait链中的所有读结点（顺序和入栈顺序相反，也就是后进先出）。
 *
 * @author shuaijunhe
 * @create 2019/10/15
 * @description
 */
public class StampedLockDemo {

    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    /**
     * 排它锁
     * @param deltaX
     * @param deltaY
     */
    private void move(double deltaX, double deltaY){
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } catch (Exception e) {
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读锁案例
     * @return
     */
    double distanceFromOrigin() {
        // 使用乐观读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 拷贝共享资源到本地方法栈中
        double currentX = x, currentY = y;
        // 如果有写锁被占用，可能造成数据不一致，所以要切换到普通读锁模式
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     * 悲观读锁案例
     * @param newX
     * @param newY
     */
    void moveIfAtOrigin(double newX, double newY) {
        // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = stampedLock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                //读锁转换为写锁
                long ws = stampedLock.tryConvertToWriteLock(stamp);

                if (ws != 0L) { //这是确认转为写锁是否成功
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            stampedLock.unlock(stamp);
        }
    }


}
