package algorithm.string;

/**
 * 把一个字符串的一部分移动到后边
 * 例如：
 * 输入“ABCDE” 3
 * 输出 “DEABC”
 *
 * @author phenix
 * @date 2020/3/10 16:54
 */
public class RotateString {

    public static void main (String[] args) {

        String str = "ABCDE";
        char[] chars = str.toCharArray();
        rotate(chars, 3);
        System.out.println(String.valueOf(chars));
    }

    private static void rotate(char[] chars, int pos){
        if(chars == null || pos <= 0 || pos >= chars.length){
            return;
        }
        reverse(chars, 0, pos - 1);
        reverse(chars, pos, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
    }

    private static void reverse(char[] chars, int start, int end){
        char temp = 0;
        while(start < end){
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
