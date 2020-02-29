import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class YesterdayCurrent {

    /**
     * 打印昨天的当前时刻
     */
    @Test
    public void test1(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        Date d = cal.getTime();
        System.out.println(d);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        String time = f.format(d);
        System.out.println("格式化结果：" + time);
    }

    @Test
    public void test2(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);

        Instant instant = yesterday.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        String time = f.format(date.getTime());
        System.out.println("格式化结果：" + time);
    }

    @Test
    public void testTime(){
        System.out.println(System.currentTimeMillis());
    }
}
