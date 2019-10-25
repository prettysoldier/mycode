- Executor：提交普通的可执行任务
- ExecutorService：提供对线程池生命周期的管理、异步任务的支持
- ScheduledExecutorService：提供对任务的周期性执行支持
- Executors: 简单工厂，共5类：
  - 固定线程数
    - newFixedThreadPool(int nThreads)
    - newFixedThreadPool(int nThreads, ThreadFactory threadFactory) 
    - 返回 ThreadPoolExecutor （ExecutorService的实现类）
  