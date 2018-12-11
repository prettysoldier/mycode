package java.operand;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/21 15:40
 **/
public class DisplacementDemo {
    public static void main(String[] args) {
        System.out.println(5 >>> 2);
        System.out.println(5 >>> 3);
        System.out.println(5 >>> 32);
        System.out.println(-5 >>> 32);
        System.out.println(-5 >>> 31);
        System.out.println(-5 >>> 63);


        System.out.println(1f - 0.9f);
        System.out.println(1f - 1f);
        System.out.println(0f);

        System.out.println(0xf0);
        System.out.println(0x0f);

        System.out.println(0.9f);
        System.out.println(0.9d);
        System.out.println(0.9f/1.0);
        System.out.println(0.9d/1.0);
    }
}
