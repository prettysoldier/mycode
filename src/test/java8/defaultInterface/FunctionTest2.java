
package test.java8.defaultInterface;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author Shuaijun He
 */
public class FunctionTest2 {

    public static void main(String[] args) {

        // Date API ,like Joda-Time
        // Clock
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        System.out.println(clock.getZone());

        Clock clock1 = Clock.systemUTC();
        long millis1 = clock1.millis();
        System.out.println(millis1);
        System.out.println(clock1.getZone());

        Clock c = Clock.system(ZoneId.of("Australia/Darwin"));
        System.out.println(c.millis());
        System.out.println(c.getZone());

        System.out.println(System.currentTimeMillis());

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        System.out.println(legacyDate);

        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
        System.out.println(ZoneId.of("Asia/Shanghai").getRules());
        // ZoneRules[currentStandardOffset=+01:00]
        // ZoneRules[currentStandardOffset=-03:00]

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);       // -4
        System.out.println(minutesBetween);     // -299

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(
            FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow.minusDays(2));
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        System.out.println(independenceDay.getDayOfWeek());

        DateTimeFormatter germanFormatter1 = DateTimeFormatter.ofLocalizedDate(
            FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        LocalDate xmas1 = LocalDate.parse("24.12.2014", germanFormatter1);
        System.out.println(xmas1);   // 2014-12-24

        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31,
            23, 59, 59);
        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY
        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439
        System.out.println(sylvester);

        DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("MM dd, yyyy - HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("05 03, 2014 - 07:13",
            formatter);
        String string = formatter.format(parsed);
        System.out.println(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13

    }
}
