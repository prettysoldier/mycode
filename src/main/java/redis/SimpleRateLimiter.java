package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 因为这几个连续的 Redis 操作都是针对同一个 key 的，使用 pipeline 可以显著􁨀升
 * Redis 存取效率。但这种方案也有缺点，因为它要记录时间窗口内所有的行为记录，如果这
 * 个量很大，比如限定 60s 内操作不得超过 100w 次这样的参数，它是不适合做这样的限流
 * 的，因为会消耗大量的存储空间。
 *
 * @author hsj
 * @create 2020/1/19
 */
public class SimpleRateLimiter {
    private Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {

        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();

        Response<Long> count;
        try (Pipeline pipe = jedis.pipelined()){
            pipe.multi();
            pipe.zadd(key, nowTs, "" + nowTs);
            pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
            count = pipe.zcard(key);
            pipe.expire(key, period + 1);
            pipe.exec();
            return count.get() <= maxCount;
        } catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(limiter.isActionAllowed("laoqian", "reply", 60, 5));
        }
    }
}
