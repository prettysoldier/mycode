package algorithm.dynamic_planning.Backpack;

/**
 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 * @author hsj
 * @create 2020/1/13
 */
public class Backpack1 {

    public static void main(String[] args) {
        backPack(10, new int[]{3, 4, 8, 5});
    }

    /**
     * O(n x m) time and O(m) memory.
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public static int backPack(int m, int[] A) {

        int[] tmp = new int[m + 1];
        for(int i = 0; i < A.length; i++){
            for(int size = m; size >= A[i]; size--){
                tmp[size] = Math.max(tmp[size], tmp[size - A[i]] + A[i]);
            }
        }
        return tmp[m];
    }
}
