package redis.hyperloglog;

import redis.clients.jedis.Jedis;

/**
 * @author shuaijunhe
 * @create 2019/3/29
 * @description
 */
public class PfTest {
    public static void main(String[] args) {
//        maxFailRate();

        testForBigData();
    }

    /**
     * 最大失败率
     */
    private static void maxFailRate(){
        Jedis jedis = new Jedis();
//        jedis.del("codehole");
        int realSize;
        double maxRate = 0;
        for (int i = 0; i < 1000; i++) {
            jedis.pfadd("codehole", "user" + i);
            long count = jedis.pfcount("codehole");
            if (count != i + 1) {
                realSize = i + 1;
                double rate = (double)(count - realSize) / realSize;
                System.out.printf("实际数量%d, hll统计%d, 错误率%f\n", realSize, count, rate);
                if(rate > maxRate){
                    maxRate = rate;
                }
            }
        }
        System.out.println("最大错误率" + maxRate);
        jedis.close();
    }

    /**
     * 大数据量测试
     */
    private static void testForBigData(){
        Jedis jedis = new Jedis();
        jedis.del("codehole");
        int realSize = 10_0000;
        for (int i = 0; i < realSize; i++) {
            jedis.pfadd("codehole", "user" + i);
        }
        long count = jedis.pfcount("codehole");

        double rate = (double)(count - realSize) / realSize;
        System.out.printf("实际数量%d, hll统计%d, 错误率%f\n", realSize, count, rate);
        jedis.close();
    }
}
