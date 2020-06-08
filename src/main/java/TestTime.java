import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestTime {

    public static List<String> ACTIVITY_DAYS = Arrays.asList("2020-03-05", "2020-03-06", "2020-03-07", "2020-03-08",
            "2020-03-09");

    private Date getPrevDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - n);
        return calendar.getTime();
    }

    @Test
    public void testDate() {
        System.out.println(getPrevDate(new Date(), 20));
    }

    @Test
    public void testDateCompare() throws Exception {
        String date = "2020-02-28 10:07:16";

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dt = format.parse(date);

        Date curDate = new Date();

        System.out.println(dt);
        System.out.println(curDate);
    }

    public static int parseInt(String s) {
        return parseInt(s, 0);
    }

    public static int parseInt(String s, int iDefault) {
        if (s == null || s.equals("")) {
            return iDefault;
        }
        if (s.equals("true")) {
            return 1;
        }
        if (s.equals("false")) {
            return 0;
        }
        try {
            s = s.replaceAll(",", "");
            int l = s.indexOf(".");
            if (l > 0) {
                s = s.substring(0, l);
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return iDefault;
        }
    }

    @Test
    public void testContain() {
        SimpleDateFormat ff = new SimpleDateFormat();
        ff.applyPattern("yyyy-MM-dd");
        String curDate = ff.format(new Date());

        System.out.println(ACTIVITY_DAYS.contains(curDate));

        System.out.println((new Date()).getTime());

        String a = "";
        System.out.println(TestTime.parseInt(a));

        int inviteNum = 11;
        int remainder = (int) Math.floor((inviteNum + 1) / 3);
        System.out.println(remainder);

        int mode = (inviteNum + 1) % 3;
        System.out.println(mode);

    }

    public Map<String, Integer> getDateDetail(String strDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(strDate, formatter);

        Map<String, Integer> detail = new HashMap<>();
        detail.put("year", ldt.getYear());
        detail.put("month", ldt.getMonthValue());
        detail.put("date", ldt.getDayOfMonth());
        detail.put("hour", ldt.getHour());
        detail.put("minute", ldt.getMinute());
        detail.put("second", ldt.getSecond());
        detail.put("week", ldt.getDayOfWeek().getValue());
        return detail;
    }

    @Test
    public void getDay() {
        String strDate = "2020-03-14 20:27:30";
        String format = "yyyy-MM-dd HH:mm:ss";

        Map<String, Integer> detail = getDateDetail(strDate, format);

        String expression = detail.get("second") + " " + detail.get("minute") + " " + detail.get("hour") + " "
                + detail.get("date") + " " + detail.get("month") + " ? " + detail.get("year");
        System.out.println(expression);

        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = formatter.parse(strDate);
        } catch (ParseException e) {}

        System.out.println(90 * 24 * 60 * 60 * 1000L);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        System.out.println(calendar.getTime());

    }

    public enum TypeEnum {
        PUSH;
    }

    @Test
    public void testEnum() {
        // System.out.println(TypeEnum.valueOf("PUSH1"));
        JSONArray ids = JSONObject.parseArray("");
        System.out.println(ids);
        if (null != ids) {
            System.out.println(ids.get(0));
        }

        System.out.println(JSONObject.toJSONString(null));

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        if (map == null || map.isEmpty()) {
            System.out.println("test");
        }

        int a = map.get(1);
        int b = map.get(2);

        System.out.println(a);
        System.out.println(b);

    }

    public Date getLastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxDate);
        return calendar.getTime();
    }

    public Date getFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minDate = calendar.getActualMinimum(Calendar.DATE);
        calendar.set(Calendar.DATE, minDate);
        return calendar.getTime();
    }

    public long getFirstDayOfMonth(long millis) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(millis);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    public long getLastDayOfMonth(long millis) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(millis);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    public Date getNextNDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + n);
        return calendar.getTime();
    }

    public Date getPreviousNHour(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        calendar.set(Calendar.HOUR, hour - n);
        return calendar.getTime();
    }

    @Test
    public void testTime() {
        Date date = new Date(getLastDayOfMonth(System.currentTimeMillis()));

        System.out.println(date);
        System.out.println(getNextNDate(date, 1));
        System.out.println(getNextNDate(new Date(), 1));

        System.out.println(new Date());
        System.out.println(getPreviousNHour(new Date(), -12));
    }
}
