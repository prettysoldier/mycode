
package java.lang.integer;

/**
 * @author Shuaijun He
 */
public class MyInteger {

    /**
     * Integer缓存范围[-128,127]
     * String integerCacheHighPropValue =
     * sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
     * 可以通过VM变量指定。但是不能超过Integer.MAX_VALUE - (-low) -1，因为缓存数组最大是Integer.MAX_VALUE
     * 
     * @author Shuaijun He
     * @param args
     */
    public static void main(String[] args) {

    }
}
