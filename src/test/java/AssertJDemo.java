import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 17:25
 **/
public class AssertJDemo {
    @Test
    public void testUsingAssertJ(){
        String s = "abcde";
        Assertions.assertThat(s).as("字符串校验，校验首尾和长度")
                .startsWith("ab").endsWith("de").hasSize(5);
    }
}
