- ThreadPoolExecutor中只有一种类型的线程，名叫Worker，它是ThreadPoolExecutor定义的内部类，同时封装着Runnable任务和执行该任务的Thread对象，我们称它为【工作线程】，它也是ThreadPoolExecutor唯一需要进行维护的线程；
- ThreadPoolExecutor内部定义了一个AtomicInteger变量——ctl，通过按位划分的方式，在一个变量中记录线程池状态和工作线程数——低29位保存线程数，高3位保存线程池状态：

- 可以看到，ThreadPoolExecutor一共定义了5种线程池状态：
  
  - RUNNING : 接受新任务, 且处理已经进入阻塞队列的任务
  - SHUTDOWN : 不接受新任务, 但处理已经进入阻塞队列的任务
  - STOP : 不接受新任务, 且不处理已经进入阻塞队列的任务, 同时中断正在运行的任务
  - TIDYING : 所有任务都已终止, 工作线程数为0, 线程转化为TIDYING状态并准备调用terminated方法
  - TERMINATED : terminated方法已经执行完成
  - ![avatar](https://segmentfault.com/img/bVbhWhH?w=1732&h=682)
- 拒绝策略
  - AbortPolicy(默认) 抛异常：RejectedExecutionException
  - DiscardPolicy ： 丢弃，什么都不做
  - DiscardOldestPolicy ：丢弃任务队列中的最近一个任务，并执行当前任务！！
  - CallerRunsPolicy ：以自身线程来执行任务，这样可以减缓新任务提交的速度。
  
