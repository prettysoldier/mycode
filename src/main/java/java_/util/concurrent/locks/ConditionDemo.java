package java_.util.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Object的wait和notify/notify是与对象监视器配合完成线程间的等待/通知机制，而Condition与Lock配合完成等待通知机制，
 * 前者是java底层级别的，后者是语言级别的，具有更高的可控制性和扩展性。两者除了在使用方式上不同外，在功能特性上还是有很多的不同：
 * Condition能够支持不响应中断，而通过使用Object方式不支持；
 * Condition能够支持多个等待队列（new 多个Condition对象），而Object方式只能支持一个；
 * Condition能够支持超时时间的设置，而Object不支持
 *
 * 问题1、await()方法会释放与此相关的锁，当signal时，是否会尝试获取锁？
 * 唤醒一个等待在condition上的线程，将该线程从等待队列中转移到同步队列中，如果在同步队列中能够竞争到Lock则可以从等待方法中返回。
 *
 *
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
public class ConditionDemo {
    ReentrantLock lock = new ReentrantLock();
    Condition con = lock.newCondition();

}
