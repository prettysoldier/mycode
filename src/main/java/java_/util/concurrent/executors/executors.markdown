- Executor：提交普通的可执行任务
- ExecutorService：提供对线程池生命周期的管理、异步任务的支持
- ScheduledExecutorService：提供对任务的周期性执行支持
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
  