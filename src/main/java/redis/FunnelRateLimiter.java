package redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流：用Java实现
 *
 * Redis 4.0 􁨀供了一个限流 Redis 模块，它叫 redis-cell。该模块也使用了漏斗算法，并提供了原子的限流指令
 * cl.throttle key 15 30 60 <quota>
 * 参数：15 : 总容量   30和60：60秒内允许30个操作  quota: 需要的额度，可选，默认是1。
 *
 * 1) (integer) 0 # 0 表示允许，1 表示拒绝
 * 2) (integer) 15 # 漏斗容量capacity
 * 3) (integer) 14 # 漏斗剩余空间left_quota
 * 4) (integer) -1 # 如果拒绝了，需要多长时间后再试(漏斗有空间了，单位秒)
 * 5) (integer) 2 # 多长时间后，漏斗完全空出来(left_quota==capacity，单位秒)
 *
 * @author hsj
 * @create 2020/1/19
 */
public class FunnelRateLimiter {

    static class Funnel {
        /**
         * 总额度
         */
        int capacity;
        /**
         * 流速
         */
        float leakingRate;
        /**
         * 剩余额度
         */
        int leftQuota;
        /**
         * 更新容量的时间
         */
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        /**
         * Funnel 对象的 make_space 方法是漏斗算法的核心。
         * 其在每次灌水前都会被调用以触发漏水，给漏斗腾出空间来。
         * 能腾出多少空间取决于过去了多久以及流水的速率。
         * Funnel 对象占据的空间大小不再和行为的频率成正比，它的空间占用是一个常量。
         */
        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuota = (int) (deltaTs * leakingRate);
            // 间隔时间太长，整数数字过大溢出
            if (deltaQuota < 0) {
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            // 腾出空间太小，最小单位是1
            if (deltaQuota < 1) {
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    /**
     * 漏斗集合，每个用户每种行为一个漏斗
     */
    private Map<String, Funnel> funnels = new HashMap<>();

    private static Object lock = new Object();
    /**
     * 某个操作是否被允许
     * @param userId
     * @param actionKey
     * @param capacity
     * @param leakingRate
     * @return
     */
    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            synchronized (lock){
                if(funnel == null){
                    funnel = new Funnel(capacity, leakingRate);
                    funnels.put(key, funnel);
                }
            }
        }
        // 需要1 个 quota
        return funnel.watering(1);
    }


}