package java_.util.concurrent.locks;

/**
 * StampedLock类，在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，
 * 该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制并发。
 *
 * 首先明确下，该类的设计初衷是作为一个内部工具类，用于辅助开发其它线程安全组件，
 * 用得好，该类可以提升系统性能，用不好，容易产生死锁和其它莫名其妙的问题
 *
 * 先来看下，为什么有了ReentrantReadWriteLock，还要引入StampedLock？
 * 读写锁如果使用不当，很容易产生“饥饿”问题：
 * 比如在读线程非常多，写线程很少的情况下，很容易导致写线程“饥饿”，虽然使用“公平”策略可以一定程度上缓解这个问题，
 * 但是“公平”策略是以牺牲系统吞吐量为代价的。
 *
 * StampedLock的特点：
 *
 *
 * @author shuaijunhe
 * @create 2019/10/15
 * @description
 */
public class MyStampedLock {

}
