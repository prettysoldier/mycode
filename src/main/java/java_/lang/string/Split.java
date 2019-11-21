package java_.lang.string;

public class Split {
    public static void main(String[] args) {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);

        final int a = 1;

        // 不行
//        char[] chars = "asd";
    }
}
