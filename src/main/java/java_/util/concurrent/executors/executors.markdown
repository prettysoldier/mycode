- Executor：提交普通的可执行任务，是最顶层的抽象核心接口， 分离了任务和任务的执行。
- ExecutorService：提供对执行器生命周期的管理、异步任务的支持
- ScheduledExecutorService：在ExecutorService基础上提供了任务的延迟执行/周期执行的功能。
- Executors: 简单工厂，共5类：
  - 固定线程数
    - newFixedThreadPool(int nThreads)
    - newFixedThreadPool(int nThreads, ThreadFactory threadFactory) 
    - 返回 ThreadPoolExecutor （ExecutorService的实现类）
  - 单个线程的线程池
    - newSingleThreadExecutor()
    - newSingleThreadExecutor(ThreadFactory threadFactory)
    - 返回的Executor实例用了一个 FinalizableDelegatedExecutorService 对象进行包装。FinalizableDelegatedExecutorService
的核心是其继承的 DelegatedExecutorService ，这是一个包装类，委托 ThreadPoolExecutor 实现了 ExecutorService 的所有方法。为什么要加一层委托层呢？因为不想暴露 ThreadPoolExecutor 的一些方法给只有单个线程的线程池。比如 setCorePoolSize 。
  - 可缓存的线程池
     * 如果线程池中没有线程可用, 则创建一个新线程并添加到池中;
     * 如果有线程长时间未被使用(默认60s), 则从缓存中移除.
     - public static ExecutorService newCachedThreadPool() {
          return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                  new SynchronousQueue<Runnable>());
      }
     - public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
           return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                   new SynchronousQueue<Runnable>(), threadFactory);
       }
  - 可延时/周期调度的线程池    
    - public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
          return new ScheduledThreadPoolExecutor(corePoolSize);
      }
    - public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
          return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
      }
  - Fork/Join线程池
    - Fork/Join线程池是比较特殊的一类线程池，在JDK1.7时才引入，其核心实现就是ForkJoinPool类。关于Fork/Join框架，我们后面会专题讲解，现在只需要知道，Executors框架提供了一种创建该类线程池的便捷方法。
    - 创建并行级别等于CPU核心数的ForkJoin线程池.
    public static ExecutorService newWorkStealingPool() {
          return new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                  null, true);
      }
    - 创建具有指定并行级别的ForkJoin线程池.
    public static ExecutorService newWorkStealingPool(int parallelism) {
          return new ForkJoinPool(parallelism, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
      }
- ThreadFactory 线程工厂，用于创建单个线程，减少手工创建线程的繁琐工作，同时能够复用工厂的特性。
这样做的好处是：一来解耦对象的创建与使用，二来可以批量配置线程信息（优先级、线程名称、是否守护线程等），以自由设置池子中所有线程的状态。
- AbstractExecutorService ExecutorService的抽象实现，为各类执行器类的实现提供基础。
- ThreadPoolExecutor 线程池Executor，也是最常用的Executor，可以以线程池的方式管理线程。
- ScheduledThreadPoolExecutor
  在ThreadPoolExecutor基础上，增加了对周期任务调度的支持。
  - schedule方法返回 ScheduledFutureTask 实现类。ScheduledFutureTask 通过 period 字段来表示任务类型
    - period >0 固定周期任务
    - period <0 固定延迟周期任务
    - 如果是周期执行，会调用runAndReset方法，此方法正常情况下不会设置任务的最终状态（即保持NEW状态），以便任务重复执行。因为Future机制是通过修改状态的控制任务是否完成的。
    然后重置time字段，并插入到队列中等待下次执行
  - DelayedWorkQueue是一种堆结构，time最小的任务会排在堆顶（表示最早过期），每次出队都是取堆顶元素，这样最快到期的任务就会被先执行。如果两个ScheduledFutureTask的time相同，就比较它们的序号——sequenceNumber，序号小的代表先被提交，所以就会先执行。
  - 在ScheduledThreadPoolExecutor中，一旦核心线程池满了，就不会再去创建工作线程。
  - 对Runnable任务进行包装，封装成ScheduledFutureTask，该类任务支持任务的周期执行、延迟执行；
  - 采用DelayedWorkQueue作为任务队列。该队列是无界队列，所以任务一定能添加成功，但是当工作线程尝试从队列取任务执行时，只有最先到期的任务会出队，如果没有任务或者队首任务未到期，则工作线程会阻塞；
  - ScheduledThreadPoolExecutor的任务调度流程与ThreadPoolExecutor略有区别，最大的区别就是，先往队列添加任务，然后创建工作线程执行任务。
  - maximumPoolSize这个参数对ScheduledThreadPoolExecutor其实并没有作用，因为除非把corePoolSize设置为0，这种情况下ScheduledThreadPoolExecutor只会创建一个属于非核心线程池的工作线程；否则，ScheduledThreadPoolExecutor只会新建归属于核心线程池的工作线程，一旦核心线程池满了，就不再新建工作线程。
  
- ForkJoinPool
  Fork/Join线程池，在JDK1.7时引入，时实现Fork/Join框架的核心类。
- ![avatar](https://segmentfault.com/img/bVbhK5v?w=1696&h=812)
      