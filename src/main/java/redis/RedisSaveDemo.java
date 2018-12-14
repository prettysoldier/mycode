package redis;

import redis.clients.jedis.Jedis;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/13 18:21
 **/
public class RedisSaveDemo {
    public static void main(String[] args) throws Exception{
        RedisSaveDemo.syncSave();
    }
    //立即保存，同步保存
    public static void syncSave() throws Exception{
        Jedis jedis=new Jedis("127.0.0.1",6379);
        for (int i = 0; i <1000; i++) {
            jedis.set("key"+i, "Hello"+i);
            System.out.println("设置key"+i+"的数据到redis");
            Thread.sleep(2);
        }
            //执行保存，会在服务器下生成一个dump.rdb数据库文件,并且是同步方法
//         jedis.save();
//        jedis.bgsave();
        jedis.close();
        System.out.println("写入完成");
    }

}
