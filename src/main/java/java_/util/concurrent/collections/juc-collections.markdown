### juc-collections集合框架

- map 表  
- set 集合
- list 列表
- queue 队列


其中阻塞队列的分类及特性如下表：

|队列特性	|有界队列|近似无界队列|无界队列|特殊队列|
|:----|:-----:|:-----:|:----:|:-----:|
|有锁算法	|ArrayBlockingQueue|	LinkedBlockingQueue、LinkedBlockingDeque	|/|	PriorityBlockingQueue、DelayQueue|
|无锁算法	|/|	/|	LinkedTransferQueue|	SynchronousQueue
