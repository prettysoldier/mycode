package redis.cluster;

import redis.clients.jedis.JedisCluster;
import utils.JsonUtil;

/**
 * 主从测试
 * @author shuaijunhe
 * @create 2019/8/9
 * @description
 */
public class MasterSlaveTest {

    public static void main(String[] args) throws Exception {

        NSJedisCluster cluster = new NSJedisCluster();
        cluster.init();
        JedisCluster jedis = cluster.getJedis();
        for(int i = 0; i < 1000000; i++){
            try {
                Thread.sleep(2000);
                jedis.set("test", i + "");
                System.out.println(cluster.getJedis().get("test"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
