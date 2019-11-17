package algorithm.wenhan_homework;

/**
 * @author heshuaijun
 * @date 2019/11/17 12:33
 */
public class Test {

    public static void main (String[] args) {
        int ret[] = mergedAndSorted(new int[]{1, 2, 3}, new int[]{2, 4, 6});
        for(int i =0; i < ret.length; i++){
            System.out.print(ret[i] + ", ");
        }
    }

    protected static int[] mergedAndSorted(int[] arr1, int[] arr2){
        if(arr1 == null || arr1.length == 0){
            return arr2;
        }
        if(arr2 == null || arr2.length == 0){
            return arr1;
        }

        int[] ret = new int[arr1.length + arr2.length];

        int j = -1;
        int a1 = 0;
        int a2 = 0;
        for( ;a1 < arr1.length && a2 < arr2.length;){
            if(arr1[a1] <= arr2[a2]){
                ret[++j] =  arr1[a1];
                a1++;
            }else{
                ret[++j] =  arr2[a2];
                a2++;
            }
        }
        if( a2 < arr2.length){
            for(;a2 < arr2.length; a2++){
                ret[++j] = arr2[a2];
            }
        } else {
            for(;a1 < arr1.length; a2++){
                ret[++j] = arr1[a1];
            }
        }
        return ret;




    }

}
