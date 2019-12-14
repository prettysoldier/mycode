package algorithm;

/**
 * @author heshuaijun
 * @date 2019/12/14 11:00
 */
public class KMP {
    /**
     * 朴素模式匹配
     *
     * @param source 目标串
     * @param pattern 模式串
     */
    private static void plain(String source, String pattern) {
        int res=0;
        int sourceLength=source.length();
        int patternLength=pattern.length();
        for(int i=0;i<=(sourceLength-patternLength);i++){
            res++;
            String str=source.substring(i, i+patternLength);
            if(str.equals(pattern)){
                p("朴素模式：匹配成功");
                break;
            }
        }
        p("朴素模式：一共匹配"+res+"次数");
    }

    //KMP算法实现
    private static void KMP(String source, String pattern) {
        int[] N= getNext(pattern);
        int res=0;
        int sLen = source.length();
        int pLen = pattern.length();
        for(int i=0;i<=(sLen-pLen);){
            res++;
            String str = source.substring(i, i+pLen);//要比较的字符串
            p(str);
            int count=getNext(pattern, str, N);
            p("移动"+count+"步");
            if(count==0){
                p("KMP：匹配成功");
                break;
            }
            i=i+count;
        }
        p("KMP：一共匹配"+res+"次数");
    }
    /**
     * 得到下一次要移动的次数
     *
     * @param pattern
     * @param str
     * @param N
     * @return 0,字符串匹配；
     */
    private static int getNext(String pattern,String str,int[] N) {
        int n = pattern.length();
        char v1[] = str.toCharArray();
        char v2[] = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]){
                if(x==0){
                    return 1;//如果第一个不相同，移动1步
                }
                return x-N[x-1];//x:第一次出现不同的索引的位置，即j
            }
            x++;
        }
        return 0;
    }
    private static int[] getNext(String pattern) {
        char[] pat=pattern.toCharArray();
        int j=pattern.length()-1;
        int[] next = new int[j+1];
        for(int i=j; i>=2; i--){
            next[i-1]=getK(i,pat);
        }
        for(int a : next)
            p(a);
        return next;
    }
    private static int getK(int j, char[] pat) {
        int x=j-2;
        int y=1;
        while (x>=0 && compare(pat, 0, x, y, j-1)) {
            x--;
            y++;
        }
        return x+1;
    }
    private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
        int n = e1-b1+1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]){
                return true;
            }
            b1++;
            b2++;
        }
        return false;
    }
    public static void p(Object obj) {
        System.out.println(obj);
    }
}
