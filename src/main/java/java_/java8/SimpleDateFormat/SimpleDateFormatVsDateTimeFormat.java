
package java_.java8.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 432:2179
 *
 * @author Shuaijun He
 */
public class SimpleDateFormatVsDateTimeFormat {

}

class DateTimeFormat_ {
    static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MMM-yyyy",
        Locale.US);
    private static String date[] = { "01-Jan-1999" };

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 300000; i++) {
            String str1 = DateTimeFormat_.date[0];
            String str2 = DateTimeFormat_.sdf.format(DateTimeFormat_.sdf
                .parse(str1));
            if (!str1.equals(str2)) {
                throw new RuntimeException(Thread.currentThread().getName()
                    + ", Expected " + str1 + " but got " + str2);
            }

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class SampleDateFormat_ {
    private static String date1[] = { "01-Jan-1999" };

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 300000; i++) {
            String str1 = SampleDateFormat_.date1[0];
            Date date = DateUtil.parse(str1);
            String str2 = DateUtil.format(date);
            if (!str1.equals(str2)) {
                throw new RuntimeException(Thread.currentThread().getName()
                    + ", Expected " + str1 + " but got " + str2);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
