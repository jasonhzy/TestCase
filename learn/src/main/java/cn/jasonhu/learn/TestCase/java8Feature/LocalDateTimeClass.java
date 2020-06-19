package cn.jasonhu.learn.TestCase.java8Feature;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期时间API，旧版存在的问题：
 * 1、非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的
 * 2、设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。
 *      java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理
 * 3、时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类
 *
 *
 * 新日期时间 API，全新的日期时间API，这套API设计合理，是线程安全的。新的日期及时间API位于 java.time 包中，下面是一些关键类。
 *  LocalDate ：表示日期，包含年月日，格式为 2019-10-16
 *  LocalTime ：表示时间，包含时分秒，格式为 16:38:54.158549300
 *  LocalDateTime ：表示日期时间，包含年月日，时分秒，格式为 2018-09-06T15:33:56.750
 *  DateTimeFormatter ：日期时间格式化类。
 *  Instant：时间戳，表示一个特定的时间瞬间。
 *  Duration：用于计算2个时间(LocalTime，时分秒)的距离
 *  Period：用于计算2个日期(LocalDate，年月日)的距离
 *  ZonedDateTime ：包含时区的时间
 */
public class LocalDateTimeClass {

    // LocalDate: 表示日期,有年月日
    @Test
    public void testLocalDate(){
        LocalDate date = LocalDate.of(2020, 4, 1);
        System.out.println("date = " + date);

        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
    }

    // LocalTime: 表示时间,有时分秒
    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.of(22, 00, 39);
        System.out.println("time = " + time);

        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);

        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
    }


    // LocalDateTime: LocalDate + LocalTime 有年月日 时分秒
    @Test
    public void testLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 4, 1, 13, 28, 59);
        System.out.println("dateTime = " + dateTime);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getHour());
        System.out.println(now.getSecond());
    }

    // LocalDateTime 类: 对日期时间的修改
    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        // 修改日期时间
        LocalDateTime setYear = now.withYear(2078);
        System.out.println("修改年份: " + setYear);
        System.out.println("now == setYear: " + (now == setYear));
        System.out.println("修改月份: " + now.withMonth(6));
        System.out.println("修改小时: " + now.withHour(9));
        System.out.println("修改分钟: " + now.withMinute(11));
        System.out.println( now.withYear(2021).withMonth(1).withHour(11).withMinute(11));
        // 再当前对象的基础上加上或减去指定的时间
        LocalDateTime localDateTime = now.plusDays(5);
        System.out.println("5天后: " + localDateTime);
        System.out.println("now == localDateTime: " + (now == localDateTime));
        System.out.println("10年后: " + now.plusYears(10));
        System.out.println("20月后: " + now.plusMonths(20));
        System.out.println("20年前: " + now.minusYears(20));
        System.out.println("5月前: " + now.minusMonths(5));
        System.out.println("100天前: " + now.minusDays(100));
    }

    // 日期时间的比较
    @Test
    public void test2() {
        // 在JDK8中，LocalDate类中使用isBefore()、isAfter()、equals()方法来比较两个日期，可直接进行比较。
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 4, 1);
        System.out.println(now.isBefore(date)); //true
        System.out.println(now.isAfter(date)); // false
    }

    //时间格式化与解析，通过 java.time.format.DateTimeFormatter 类可以进行日期时间解析与格式化。
    @Test
    public void test7(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(dtf);
        System.out.println(format);  //2020-03-31 22:15:50

        // 将字符串解析为日期时间
        LocalDateTime parse = LocalDateTime.parse("1985-09-23 10:12:22", dtf);
        System.out.println("parse = " + parse);
    }

    //Instant 时间戳/时间线，内部保存了从1970年1月1日 00:00:00以来的秒和纳秒。
    @Test
    public void test3() {
        Instant now = Instant.now();
        System.out.println("当前时间戳 = " + now);
        // 获取从1970年1月1日 00:00:00的秒
        System.out.println(now.getNano());
        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        Instant instant = Instant.ofEpochSecond(5);
        System.out.println(instant);

        //毫秒数转LocalDateTime
        Instant instant1 = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        System.out.println(localDateTime);
    }

    // Duration/Period 类: 计算日期时间差
    @Test
    public void test4() {
        // Duration计算时间的距离
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 15, 20);
        Duration duration = Duration.between(time, now);
        System.out.println("相差的天数:" + duration.toDays());
        System.out.println("相差的小时数:" + duration.toHours());
        System.out.println("相差的分钟数:" + duration.toMinutes());
        // Period计算日期的距离
        LocalDate nowDate = LocalDate.now();
        LocalDate date = LocalDate.of(1998, 8, 8);
        // 让后面的时间减去前面的时间
        Period period = Period.between(date, nowDate);
        System.out.println("相差的年:" + period.getYears());
        System.out.println("相差的月:" + period.getMonths());
        System.out.println("相差的天:" + period.getDays());
    }

    /**
     * 有时我们可能需要获取例如：将日期调整到“下一个月的第一天”等操作。可以通过时间校正器来进行。
     * TemporalAdjuster : 时间校正器。
     * TemporalAdjusters : 该类通过静态方法提供了大量的常用TemporalAdjuster的实现。
     *
     */
    @Test
    public void testTemporalAdjuster() {
        LocalDate ld = LocalDate.now();
        System.out.println(ld.with(TemporalAdjusters.firstDayOfMonth()));      // 2020-03-01 月初
        System.out.println(ld.with(TemporalAdjusters.firstDayOfNextYear()));   // 2021-01-01 年初
        System.out.println(ld.with(TemporalAdjusters.lastDayOfMonth()));       // 2020-03-31 月末
        System.out.println(ld.with(TemporalAdjusters.lastDayOfYear()));        // 2020-12-31 年末
        System.out.println(ld.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));           // 2020-04-05 下周日
        System.out.println(ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))); // 2020-03-30 前一个或相同的周一
    }

    /**
     * 设置日期时间的时区
     * Java8 中加入了对时区的支持，LocalDate、LocalTime、LocalDateTime是不带时区的，带时区的日期时间类分别
     * 为：ZonedDate、ZonedTime、ZonedDateTime。其中每个时区都对应着 ID，ID的格式为 “区域/城市” 。例如 ：Asia/Shanghai 等。
     * ZoneId：该类中包含了所有的时区信息。
     */
    @Test
    public void test5() {
        // 1.获取所有的时区ID
        // ZoneId.getAvailableZoneIds().forEach(System.out::println);
        // 不带时间,获取计算机的当前时间
        LocalDateTime now = LocalDateTime.now(); // 中国使用的东八区的时区.比标准时间早8个小时
        System.out.println("now = " + now);    //now = 2020-03-31T22:43:42.289

        // 2.操作带时区的类
        // now(Clock.systemUTC()): 创建世界标准时间
        ZonedDateTime bz = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("bz = " + bz);      //bz = 2020-03-31T14:43:42.290Z

        // now(): 使用计算机的默认的时区,创建日期时间
        ZonedDateTime now1 = ZonedDateTime.now();
        System.out.println("now1 = " + now1); // now1 = 2020-03-31T22:43:42.290+08:00[Asia/Shanghai]

        // 使用指定的时区创建日期时间
        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("America/Vancouver"));
        System.out.println("now2 = " + now2); // now2 = 2020-03-31T07:43:42.293-07:00[America/Vancouver]

        // 修改时区
        // withZoneSameInstant: 即更改时区,也更改时间
        ZonedDateTime withZoneSameInstant = now2.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println("withZoneSameInstant = " + withZoneSameInstant); //withZoneSameInstant = 2020-03-31T22:43:42.293+08:00[Asia/Shanghai]

        // withZoneSameLocal: 只更改时区,不更改时间
        ZonedDateTime withZoneSameLocal = now2.withZoneSameLocal(ZoneId.of("Asia/Shanghai"));
        System.out.println("withZoneSameLocal = " + withZoneSameLocal); // withZoneSameLocal = 2020-03-31T07:43:42.293+08:00[Asia/Shanghai]
    }
}
