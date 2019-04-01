package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 测试Pipeline的效率
 * @author shuaijunhe
 */
public class PipeLineDemo {


    public static void main(String[] args) throws Exception{

        testPipeLineAndNormal(RedisUtil.getJedisClient());
    }
    /**
     * 测试普通模式与PipeLine模式的效率：
     * 测试方法：向redis中插入10000组数据
     */
    private static void testPipeLineAndNormal(Jedis jedis) throws Exception{

        int size = 10000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("the jedis total time is:" + (end - start));


        Pipeline pipe = jedis.pipelined();
        long startPipe = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            pipe.set(String.valueOf(i), String.valueOf(i));
        }
        pipe.sync();
        long endPipe = System.currentTimeMillis();
        System.out.println("the pipe total time is:" + (endPipe - startPipe));

        BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            logQueue.put("i=" + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("the pipe total time is:" + (stop - begin));

    }

}
