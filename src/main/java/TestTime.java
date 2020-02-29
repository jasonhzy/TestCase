import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestTime {

    private Date getPrevDate(Date date, int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - n);
        return calendar.getTime();
    }

    @Test
    public void testDate(){
        System.out.println(getPrevDate(new Date(), 20));
    }


    @Test
    public void testDateCompare() throws Exception{
        String date = "2020-02-28 10:07:16";

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dt = format.parse(date);

        Date curDate = new Date();

        System.out.println(dt);
        System.out.println(curDate);

        System.out.println(curDate.before(dt));
    }
}
