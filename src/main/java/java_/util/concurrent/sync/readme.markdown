### juc-sync
这里的juc-sync同步器框架，是指java.util.concurrent包下一些辅助同步器类，每个类都有自己适合的使用场景：
- CountDownLatch	倒数计数器，构造时设定计数值，当计数值归零后，所有阻塞线程恢复执行；其内部实现了AQS框架
- CyclicBarrier	循环栅栏，构造时设定等待线程数，当所有线程都到达栅栏后，栅栏放行；其内部通过ReentrantLock和Condition实现同步
- Semaphore	信号量，类似于“令牌”，用于控制共享资源的访问数量；其内部实现了AQS框架
- Exchanger	交换器，类似于双向栅栏，用于线程之间的配对和数据交换；其内部根据并发情况有“单槽交换”和“多槽交换”之分
- Phaser	多阶段栅栏，相当于CyclicBarrier的升级版，可用于分阶段任务的并发控制执行；其内部比较复杂，支持树形结构，以减少并发带来的竞争