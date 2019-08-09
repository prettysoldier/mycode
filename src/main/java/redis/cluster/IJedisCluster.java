package redis.cluster;


import redis.clients.jedis.JedisCluster;

/**
 * 
 * redis集群接口
 * 
 */
public interface IJedisCluster {

	/**
	 * 获取redis集群对象
	 * 
	 * @return
	 */
	JedisCluster getJedis();
	
	String getKey(String... args);
}
