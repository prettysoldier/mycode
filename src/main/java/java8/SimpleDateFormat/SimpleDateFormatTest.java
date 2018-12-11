
package java8.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 1.需要的时候创建新实例
 * 2.使用同步：同步SimpleDateFormat对象
 * 3.使用ThreadLocal：
 * 4.抛弃JDK，使用其他类库中的时间格式化类,使用Apache commons
 * 里的FastDateFormat,使用Joda-Time类库来处理时间相关问题
 *
 * @author Shuaijun He
 */
public class SimpleDateFormatTest {

}

/**
 * 问题重现
 *
 * @author Shuaijun He
 */
class DateFormatTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",
        Locale.US);
    private static String date[] = { "01-Jan-1999", "01-Jan-2000",
        "01-Jan-2001", "01-Jan-2002" };

    public static void main(String[] args) {
        for (int i = 0; i < DateFormatTest.date.length; i++) {
            final int temp = i;
            new Thread(
                () -> {
                    try {
                        while (true) {
                            String str1 = DateFormatTest.date[temp];
                            String str2 = DateFormatTest.sdf
                                .format(DateFormatTest.sdf.parse(str1));
                            System.out.println(Thread.currentThread().getName()
                                + ", " + str1 + "," + str2);
                            if (!str1.equals(str2)) {
                                throw new RuntimeException(Thread
                                    .currentThread().getName()
                                    + ", Expected "
                                    + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
                }).start();
        }
    }
}

/**
 * 每次new可解决，浪费内存；还可以加同步锁，但是效率低
 *
 * @author Shuaijun He
 */
class DateFormatTest_solve1 {
    private static String date[] = { "01-Jan-1999", "01-Jan-2000",
        "01-Jan-2001", "01-Jan-2002" };

    public static void main(String[] args) {
        for (int i = 0; i < DateFormatTest_solve1.date.length; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    while (true) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                            "dd-MMM-yyyy", Locale.US);
                        String str1 = DateFormatTest_solve1.date[temp];
                        String str2 = sdf.format(sdf.parse(str1));
                        System.out.println(Thread.currentThread().getName()
                            + ", " + str1 + "," + str2);
                        if (!str1.equals(str2)) {
                            throw new RuntimeException(Thread.currentThread()
                                .getName()
                                + ", Expected "
                                + str1
                                + " but got "
                                + str2);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("parse failed", e);
                }
            }).start();
        }
    }
}

/**
 * DateTimeFormatter是线程安全的
 *
 * @author Shuaijun He
 */
class DateFormatTest_solve2 {
    static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MMM-yyyy",
        Locale.US);
    private static String date[] = { "01-Jan-1999", "01-Jan-2000",
        "01-Jan-2001", "01-Jan-2002" };

    public static void main(String[] args) {
        for (int i = 0; i < DateFormatTest_solve2.date.length; i++) {
            final int temp = i;
            new Thread(
                () -> {
                    try {
                        while (true) {

                            String str1 = DateFormatTest_solve2.date[temp];
                            String str2 = DateFormatTest_solve2.sdf
                                .format(DateFormatTest_solve2.sdf.parse(str1));
                            System.out.println(Thread.currentThread().getName()
                                + ", " + str1 + "," + str2);
                            if (!str1.equals(str2)) {
                                throw new RuntimeException(Thread
                                    .currentThread().getName()
                                    + ", Expected "
                                    + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
                }).start();
        }
    }
}
