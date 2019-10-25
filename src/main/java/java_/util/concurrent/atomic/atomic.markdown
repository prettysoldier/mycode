
###无锁算法
所谓“无锁算法”，就是CAS。其实底层就是通过Unsafe类实现的一种比较并交换的算法，大致的结构如下：
 * boolean compareAndSet(expectedValue, updateValue);
 * 当希望修改的值与expectedValue相同时，则尝试将值更新为updateValue，更新成功返回true，否则返回false。
 
### juc-atomic 共17个类

|Integer|Long|Boolean|Reference|Double|
|:----|:-----:|:-----:|:----:|:-----:|
|AtomicInteger| AtomicLong |AtomicBoolean |AtomicReference |
|AtomicIntegerArray| AtomicLongArray|/| AtomicReferenceArray |
|AtomicIntegerFieldUpdater |AtomicLongFieldUpdater|/|AtomicReferenceFieldUpdater |
|/|/|/| AtomicMarkableReference AtomicStampedReference |
|/|LongAdder|/|/| DoubleAdder| 
|/|LongAccumulator |/|/|DoubleAccumulator| 
|Striped64 |