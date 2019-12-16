package algorithm;

/**
 * 一定要注意toCharArray方法是返回一个新的数组！而不是原数组。
 * @author heshuaijun
 * @date 2019/12/14 21:51
 */
public class ConcatMaxString {

    public static void main (String[] args) {
        String s = "abc";
        System.out.println(reverse(s));
        System.out.println(reverse2(s));
    }

    /**
     * 比较原字符串与反转后字符串大小
     * @param s
     * @return
     */
    public static String reverse2(String s){
        String rev = new StringBuilder(s).reverse().toString();
        if(rev.compareTo(s) > 0){
            return rev;
        }
        return s;
    }

    /**
     * 比较原字符串与反转后字符串大小
     * @param s
     * @return
     */
    public static String reverse(String s){
        if(s == null || s.length() <= 1 ){
            return s;
        }

        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length / 2; i++){
            if(chars[i] == chars[chars.length - 1 - i]){
                continue;
            }
            if(chars[i] < chars[chars.length - 1 - i]){
                break;
            }
            if(chars[i] > chars[chars.length - 1 - i]){
                return s;
            }
        }
        for(int i = 0; i < chars.length / 2; i++){

            if(chars[i] < chars[chars.length - 1 - i]){
                char tmp = chars[i];
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = tmp;
            }
        }
        return String.valueOf(chars);
    }
}
