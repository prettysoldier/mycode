package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合与判断素数
 * @author heshuaijun
 * @date 2019/12/14 20:36
 */
public class CombinationAndJudgePrimeNumber {

    public static void main (String[] args) {

        System.out.println(getWays(new int[]{3,9,23,12,34,65,32,19,81}, 4));
    }

    public static int getWays(int[] a, int k) {

        List<List<Integer>> dataAll = getCombination(a, 0, a.length, k);
        int ret = 0;
        for(List<Integer> e : dataAll){
            int sum = 0;
            for(int i : e){
                sum += i;
            }
            if(isPrimeNum(sum)){
                ret++;
            }
        }
        return ret;
    }

    public static List<List<Integer>> getCombination (int[] a, int start, int end, int k){
        if(k == 1){
            List<List<Integer>> data = new ArrayList<>();
            if(end - start < k){
                return data;
            }
            for(int i = start; i < end; i++){
                List<Integer> list = new ArrayList<>();
                list.add(a[i]);
                data.add(list);
            }
            return data;
        }
        List<List<Integer>> dataAll = new ArrayList<>();
        for(int i = start; i < end; i++){
            List<List<Integer>> data = getCombination(a, i+1, end, k-1);
            for(List<Integer> e : data){
                e.add(a[i]);
            }

            dataAll.addAll(data);
        }


        return dataAll;
    }

    public static boolean isPrimeNum(int num){
        if(num < 2){
            return false;
        }
        for(int i = 2; i < num / 2; i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }


}
