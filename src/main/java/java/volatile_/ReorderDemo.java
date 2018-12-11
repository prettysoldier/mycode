package java.volatile_;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/7 13:53
 **/
public class ReorderDemo {
    public static void main(String[] args) {

    }

    /**
     * 字节码显示：编译器并没有进行重排列
     */
    public void run(){
        int x = 1;
        int y = 2;
        int z = 3;

        x = x + 1;

        int sum = x + y + z;
    }
}
