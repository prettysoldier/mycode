package java_.util.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference提供了以无锁的方式访问共享资源的能力
 * 该示例并没有使用锁，而是使用自旋+CAS的无锁操作保证共享变量的线程安全。
 * 1000个线程，每个线程对金额增加1，最终结果为2000，如果线程不安全，最终结果应该会小于2000。
 *
 * CAS操作可能存在ABA的问题，
 * 一般来讲这并不是什么问题，比如数值运算，线程其实根本不关心变量中途如何变化，只要最终的状态和预期值一样即可。
 *
 * 有些操作会依赖于对象的变化过程，此时的解决思路一般就是使用版本号。
 * 在变量前面追加上版本号，每次变量更新的时候把版本号加一，那么A－B－A 就会变成1A - 2B - 3A。
 * 使用AtomicStampedReference
 *
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class AtomicReferenceTest {

    public static void main(String[] args) throws InterruptedException {

        AtomicReference<Integer> ref = new AtomicReference<>(new Integer(1000));

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Task(ref), "Thread-" + i);
            list.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }

        System.out.println(ref.get());
    }
    static class Task implements Runnable {
        private AtomicReference<Integer> ref;

        Task(AtomicReference<Integer> ref) {
            this.ref = ref;
        }

        /**
         * 该示例并没有使用锁，而是使用自旋+CAS的无锁操作保证共享变量的线程安全。
         * 1000个线程，每个线程对金额增加1，最终结果为2000，如果线程不安全，最终结果应该会小于2000。
         */
        @Override
        public void run() {
            for (; ; ) {    //自旋操作
                Integer oldV = ref.get();
                if (ref.compareAndSet(oldV, oldV + 1)){
                    break;
                }
            }
        }
    }
}

