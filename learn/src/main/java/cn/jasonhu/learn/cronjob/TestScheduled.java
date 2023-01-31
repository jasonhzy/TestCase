package cn.jasonhu.learn.cronjob;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jason hu
 * @since 2021/11/4 11:29
 */
@Component
public class TestScheduled {

    DateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    int[] delays = new int[]{8, 3, 6, 2, 2};
    int index = 0;

    //@Scheduled(cron = "0/5 * * * * ?}")
    @Scheduled(fixedDelay = 3000)
    //@Scheduled(fixedRate = 5000)
    public void testCron() {
        try {
            if (index > delays.length - 1) {
                if (index == delays.length) {
                    System.out.println(
                            "---------- test end at " + sdf.format(new Date()) + " ---------");
                }
                index++;
                return;
            } else {
                System.out.println(index + ":start run at " + sdf.format(new Date()));
            }
            Thread.sleep(delays[index] * 1000);
            System.out.println(index + ":end run at " + sdf.format(new Date()));
            index++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
