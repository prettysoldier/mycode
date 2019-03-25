package redis;

import redis.clients.jedis.Jedis;

/**
 * @author shuaijunhe
 * @create 2019/3/25
 * @description
 */
public class RedisUtil {

    private static Jedis jedis = new Jedis("127.0.0.1",6379);

    public static Jedis getJedisClient(){
        return jedis;
    }


}
