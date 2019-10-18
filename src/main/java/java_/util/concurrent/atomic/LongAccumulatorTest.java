package java_.util.concurrent.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAccumulator是LongAdder的增强版。LongAdder只能针对数值的进行加减运算，而LongAccumulator提供了自定义的函数操作
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class LongAccumulatorTest {
    LongAccumulator la = new LongAccumulator((x, y) -> x + y, 0);
}
