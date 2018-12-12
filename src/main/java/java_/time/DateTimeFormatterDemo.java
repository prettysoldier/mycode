package java_.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatterDemo {

    public static void main(String[] args) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                // 直接使用常量创建DateTimeFormatter格式器
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                // 使用本地化的不同风格来创建DateTimeFormatter格式器
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
                DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),
                // 根据模式字符串来创建DateTimeFormatter格式器
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        };
        LocalDateTime date = LocalDateTime.now();
        // 依次使用不同的格式器对LocalDateTime进行格式化
        for(int i = 0 ; i < formatters.length ; i++)
        {
            // 下面两行代码的作用相同
            System.out.println(date.format(formatters[i]));
            System.out.println(formatters[i].parse(formatters[i].format(date)));
            System.out.println("-------------------------");
        }


    }
}
