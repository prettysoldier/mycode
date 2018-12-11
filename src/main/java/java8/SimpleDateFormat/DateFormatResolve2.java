
package java8.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 使用ThreadLocal: 每个线程都将拥有自己的SimpleDateFormat对象副本。
 *
 * @author Shuaijun He
 */
public class DateFormatResolve2 {
    private static String date[] = { "01-Jan-1999", "01-Jan-2000",
        "01-Jan-2001", "01-Jan-1999", "11-Jan-2000", "12-Jan-2001",
        "13-Jan-1999", "14-Jan-2000", "15-Jan-2001", "16-Jan-1999",
        "17-Jan-2000", "18-Jan-2001" };

    public static void main(String[] args) {
        for (int i = 0; i < DateFormatResolve2.date.length; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    while (true) {
                        String str1 = DateFormatResolve2.date[temp];
                        Date date = DateUtil.parse(str1);
                        String str2 = DateUtil.format(date);
                        System.out.println(str1 + "," + str2);
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

class DateUtil {
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<>();

    public static Date parse(String str) throws Exception {
        SimpleDateFormat sdf = DateUtil.local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            DateUtil.local.set(sdf);
        }
        return sdf.parse(str);
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = DateUtil.local.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            DateUtil.local.set(sdf);
        }
        return sdf.format(date);
    }
}