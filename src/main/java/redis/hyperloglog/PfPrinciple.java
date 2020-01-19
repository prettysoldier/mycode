package redis.hyperloglog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数的数量与这组随机数的末尾连续0的位数最大值存在关系：
 * N 约等于 2^K（N:集合元素总数，K:集合中末尾为0的最大位数）
 *
 * 问题：如果 N 介于 2^K 和 2^(K+1) 之间，用这种方式估计的值都等于 2^K，这明显是不合
 * 理的。这里可以采用多个 BitKeeper，然后进行加权估计，就可以得到一个比较准确的值。见
 *
 * @author shuaijunhe
 * @create 2019/3/29
 * @description
 */
public class PfPrinciple {

    public static void main(String[] args) {
        for (int i = 1000; i < 100000; i += 100) {
            Experiment exp = new Experiment(i);
            exp.work();
            exp.debug();
        }
    }

    static class BitKeeper {
        private int maxbits;
        public void random() {
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }
        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }
    static class Experiment {
        private int n;
        private BitKeeper keeper;
        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }
        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }
        public void debug() {
            System.out.printf("重试次数：%d  log2(n):%.2f 末尾的连续0的位数的最大值：%d\n", this.n, Math.log(this.n) / Math.log(2),
                    this.keeper.maxbits);
        }
    }

}
