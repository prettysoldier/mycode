package algorithm;

import java.util.Arrays;

/**
 * 1753. 写作业
 * n个人，他们每个人需要独立做m份作业。
 * 第i份作业需要花费cost[i]的时间。由于每个人的空闲时间不同，第i个人有val[i]的时间，这代表他做作业的总时间不会超过val[i]。每个人都按照顺序，从1号作业开始，然后做2号，3号...... 现在，你需要计算出他们最多花了多少的时间。
 *
 * 样例
 * 样例 1:
 *
 * 给定`cost=[1,2,3,5]`，`val=[6,10,4]`，返回`15`。
 * 输入:
 * [1,2,3,5]
 * [6,10,4]
 * 输出
 * 15
 *
 * 解释:
 * 第一个人可以完成1号作业，2号作业，3号作业，1+2+3<=6。
 * 第二个人可以完成1号作业，2号作业，3号作业，无法完成4号作业，1+2+3<=10，1+2+3+5>10。
 * 第三个人可以完成1号作业，2号作业，无法完成3号作业，1+2<=4，1+2+3>4。
 * 1+2+3+1+2+3+1+2=15，返回15。
 * 样例 2:
 *
 * 给定 `cost=[3,7,3,2,5]`,`val=[10,20,12,8,17,25]`,返回`78`.
 * 输入:
 * [3,7,3,2,5]
 * [10,20,12,8,17,25]
 * 输出:
 * 78
 *
 * 解释:
 * 第一个人可以完成1号作业，2号作业, 3 + 7<=10.
 * 第二个人可以完成1号作业，2号作业，3号作业，4号作业和5号作业, 3+7+3+2+5<=20
 * 第三个人可以完成1号作业，2号作业，无法完成三号作业,  3+7<=12,3+7+3>12.
 * 第四个人可以完成1号作业，无法完成2号作业 , 3<=8,7+3>8.
 * 第五个人可以完成1号作业，2号作业，3号作业，4号作业，无法完成5号作业,3+7+3+2<=17,3+7+3+2+5>17.
 * 第六个人可以完成1号作业，2号作业，3号作业，4号作业和5号作业, 3+7+3+2+5<=25
 * 3+7+3+7+3+2+5+3+7+3+3+7+3+2+3+7+3+2+5=78, 返回 78.
 * 注意事项
 * 1<=n<=100000
 * 1<=m<=100000
 * 1<=val[i]<=100000
 * 1<=cost[i]<=100000
 *
 * @author hsj
 * @create 2019/12/25
 */
public class DoHomeWork {
    /**
     * 先算出第几个作业花多少时间
     * 然后使用二分法判断某个人能做完多少作业，即某个人的时间在数组中的位置。
     *
     * nlog(m)
     * @param cost
     * @param val
     * @return
     */
    public long doingHomework(int[] cost, int[] val) {

        // 记录第几个作业花多少时间
        int[] d = new int[cost.length];
        d[0] = cost[0];
        for(int i = 1; i < cost.length; i++){
            d[i] = d[i - 1] + cost[i];
        }

        long data = 0;
        for(int t : val){
            int r = Arrays.binarySearch(d, t);
            if(r >= 0){
                data += d[r];
            }else if(-r - 1 -1 >= 0){
                data += d[-r - 1 -1];
            }
        }
        return data;
    }

    /**
     * 最简单的实现，会有大量的重复计算
     * @param cost
     * @param val
     * @return
     */
    public long doingHomework2(int[] cost, int[] val) {
        long tmp;
        long sum = 0;
        for(int v : val){
            tmp = 0;
            for(int c : cost){
                if(tmp + c > v){
                    break;
                }
                tmp += c;
            }
            sum += tmp;
        }
        return sum;
    }

    public static void main(String[] args) {

        long time = new  DoHomeWork().doingHomework(new int[]{1,2,3,5}, new int[]{6,10,4});
        System.out.println(time);
    }


}
