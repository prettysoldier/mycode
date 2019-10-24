### Queue 接口：
 * 每种操作类型，都给出了两种方法，区别就是其中一种操作在队列的状态不满足某些要求时，会抛出异常；另一种，则直接返回特殊值（如null）。
 * 抛出异常：add(e)  、remove()、  element()
 * 返回特殊值（null，false）：offer(e) 、poll()、 peek()
 
### Deque 接口：
 - 继承Queue
 - 增加了一套addFirst() addLast() 等12个
 - 增加了栈的操作：push()/pop()/peek()
### BlockingQueue 接口：
 * “阻塞队列”通常利用了“锁”来实现，也就是会阻塞调用线程，
 其使用场景一般是在“生产者-消费者”模式中，用于线程之间的数据交换或系统解耦。
 * 对于每种基本方法，“抛出异常”和“返回特殊值”的方法定义和Queue是完全一样的。
 * BlockingQueue只是增加了两类和阻塞相关的方法：
      * put(e)
      * offer(e, time, unit)
      * take()
      * poll(time, unit)
 * 除此之外，BlockingQueue还具有以下特点：
      * BlockingQueue队列中不能包含null元素；
      * BlockingQueue接口的实现类都必须是线程安全的，实现类一般通过“锁”保证线程安全；
      * BlockingQueue 可以是限定容量的。remainingCapacity()方法用于返回剩余可用容量，对于没有容量限制的BlockingQueue实现，该方法总是返回Integer.MAX_VALUE 。

### BlockingDeque 接口：
* 继承了 BlockingQueue 和 Deque
* 增加了阻塞方法 8个
putFirst() putLast() takeFirst() takeLast() 
offerFirst(long timeout, TimeUnit unit) offerLast(long timeout, TimeUnit unit)
pollFirst(long timeout, TimeUnit unit) pollLast(long timeout, TimeUnit unit)
* 增加了一套addFirst() addLast() 等12个