package redis.cluster;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * 
 * redis集群对象
 * 
 */
public class NSJedisCluster implements IJedisCluster {

	private static final Logger log = LoggerFactory.getLogger(NSJedisCluster.class);

	/**
	 * 默认超时时间
	 */
	public static final int DEFALT_TIMEOUT = 6000;

	/**
	 * 默认最大重定向数
	 */
	public static final int DEFALT_MAX_REDIRECTIONS = 5;

	/**
	 * 默认最大连接数
	 */
	public static final int DEFALT_MAX_TOTAL = 5;

	/**
	 * 默认最大空闲数
	 */
	public static final int DEFALT_MAX_IDLE = 5;

	/**
	 * 默认最小空闲数
	 */
	public static final int DEFALT_MIN_IDLE = 5;

	/**
	 * 主机关键字
	 */
	public static final String KEY_HOST = "host";

	/**
	 * 端口关键字
	 */
	public static final String KEY_PORT = "port";

	/**
	 * 超时时间
	 */
	protected int timeout;

	/**
	 * 最大重定向数
	 */
	protected int maxRedirections;

	/**
	 * 集群节点集合
	 */
	protected List<Map<String, String>> jedisClusterNode;

	/**
	 * 线程池总数
	 */
	protected int poolMaxTotal;

	/**
	 * 最大空闲数
	 */
	protected int poolMaxIdle;

	/**
	 * 最小空闲数
	 */
	protected int poolMinIdle;

	private String redisPrefix;
	
	/**
	 * jedis集群对象
	 */
	private JedisCluster jedis;

	public NSJedisCluster() {
	}

	/**
	 * 初始化
	 */
	public void init() {

		log.debug("--开始初始化redis集群--");
		setDefalt();
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(this.poolMaxIdle);
		poolConfig.setMinIdle(this.poolMinIdle);
		poolConfig.setMaxTotal(this.poolMaxTotal);
		List<Map<String, String>> nodes = this.jedisClusterNode;
		Set<HostAndPort> clusterNode = new HashSet<HostAndPort>();
		for (Map<String, String> map : nodes) {
			clusterNode.add(new HostAndPort(map.get(KEY_HOST), Integer.parseInt(map.get(KEY_PORT))));
		}
		this.jedis = new JedisCluster(clusterNode, this.timeout, this.maxRedirections, poolConfig);
		log.debug("--结束初始化redis集群--");
	}

	/**
	 * 获取redis集群对象
	 */
	private void setDefalt() {

		if (this.poolMaxIdle == 0) {
			this.poolMaxIdle = DEFALT_MAX_IDLE;
		}
		if (this.maxRedirections == 0) {
			this.maxRedirections = DEFALT_MAX_REDIRECTIONS;
		}
		if (this.poolMaxTotal == 0) {
			this.poolMaxTotal = DEFALT_MAX_TOTAL;
		}
		if (this.poolMinIdle == 0) {
			this.poolMinIdle = DEFALT_MIN_IDLE;
		}
		if (this.jedisClusterNode == null) {
		    this.jedisClusterNode = new ArrayList<>(6);

            jedisClusterNode.add(getNode("10.100.137.16", "6379"));
            jedisClusterNode.add(getNode("10.100.137.16", "6380"));
            jedisClusterNode.add(getNode("10.100.137.16", "6381"));

            jedisClusterNode.add(getNode("10.100.137.16", "6382"));
            jedisClusterNode.add(getNode("10.100.137.16", "6383"));
            jedisClusterNode.add(getNode("10.100.137.16", "6384"));
        }
	}

	private static Map<String, String> getNode(String host, String port){

        Map<String, String> node = new HashedMap();
        node.put("host", host);
        node.put("port", port);
        return node;
    }

	/**
	 * 获取redis集群对象
	 */
	@Override
	public JedisCluster getJedis() {

		return this.jedis;
	}

	@Override
	public String getKey(String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append(redisPrefix);
		for(String str : args){
			sb.append(":").append(str);
		}
		return sb.toString();
	}

	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public int getPoolMaxTotal() {
		return poolMaxTotal;
	}

	public void setPoolMaxTotal(int poolMaxTotal) {
		this.poolMaxTotal = poolMaxTotal;
	}

	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}

	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}

	public int getPoolMinIdle() {
		return poolMinIdle;
	}

	public void setPoolMinIdle(int poolMinIdle) {
		this.poolMinIdle = poolMinIdle;
	}

	public List<Map<String, String>> getJedisClusterNode() {
		return jedisClusterNode;
	}

	public void setJedisClusterNode(List<Map<String, String>> jedisClusterNode) {
		this.jedisClusterNode = jedisClusterNode;
	}

	public String getRedisPrefix() {
		return redisPrefix;
	}

	public void setRedisPrefix(String redisPrefix) {
		this.redisPrefix = redisPrefix;
	}

	
}
