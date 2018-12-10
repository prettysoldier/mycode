package test.java.operand;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/10 16:24
 **/
public class LogicOperandTest {
    public static void main(String[] args) {
        System.out.println(false && true || true);// true
        System.out.println(false || true && true);// true
    }
}
