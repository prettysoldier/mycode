package redis.cluster;

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
        for(int i = 0; i < 1000000; i++){
            Thread.sleep(3000);
            cluster.getJedis().set("test", i + "");
            System.out.println(cluster.getJedis().get("test"));
        }
    }

}
