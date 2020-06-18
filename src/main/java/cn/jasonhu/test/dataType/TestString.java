package cn.jasonhu.test.dataType;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

public class TestString {

    @Test
    public void test1() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4; // jad 反编译结果即：String s6 = (new StringBuilder()).append(s3).append(s4).toString();
        String s7 = new String("Programming");
        String s8 = s3.concat(s4);
        String s9 = s1.concat("");
        String s10 = "Program" + new String("ming"); //jad 反编译结果即：String s10 = (new StringBuilder()).append("Program").append(new String("ming")).toString();
        System.out.println(s1 == s2); // false
        System.out.println(s2 == s2.intern()); // false

        System.out.println(s1 == s5); // true
        // 字符串的+ 或者 +=操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder
        // 对象用toString方法处理成String对象
        // 可以用javap -c cn.jasonhu.test.dataType.TestString.class命令获得class文件对应的JVM字节码指令就可以看出来
        //参考：https://droidyue.com/blog/2014/08/30/java-details-string-concatenation/
        System.out.println(s1 == s6); // false

        //调用intern后，首先检查字符串常量池中是否有该对象的引用，如果存在，，则将这个引用返回给变量，否则将引用加入并返回给变量。
        System.out.println(s1 == s6.intern()); // true
        System.out.println(s2 == s7); // false

        //contact方法源码
        System.out.println(s1 == s8); // false
        System.out.println(s1 == s9); // true
        System.out.println(s1 == s10); // false
    }

    // String 字符串常量，不可变的，线程安全
    // StringBuffer 字符串变量（线程安全）
    // StringBuilder 字符串变量（非线程安全）
    // StringBuffer和StringBuilder，有公共父类AbstractStringBuilder(抽象类)，区别StringBuffer方法上加了synchronized

    @Test
    public void testlen() {
        String str = "";
        System.out.print(str.split(",").length);
    }

    @Test
    public void testReplaceAll() {
        String classFile = "com.jd.".replaceAll(".", "/") + "MyClass.class";
        System.out.println(classFile);
    }

    @Test
    public void testResult() {
        // 为初始化，编译不通过。原因：成员变量有初始值，而局部变量没有初始值
        // String s;
        // System.out.println("s="+s);
    }

    @Test
    public void testSubstring() {
        String subStr = "123456789";
        // 示例1、正常情况：由下面两个例子可以发现下标是从1而不是0开始的
        System.out.println(subStr.substring(0));// 输出:123456789
        System.out.println(subStr.substring(1));// 输出:23456789
        System.out.println(subStr.substring(subStr.length()));// 输出空符串

        // 示例2、字符超长（直接报错）
        // System.out.println(subStr.substring(subStr.length()+1));
        // 报错：java.lang.StringIndexOutOfBoundsException: String index out of range: -1

        // 示例3、输入负数（直接报错，可我怎么听说是反着截取出来的呢，难道是我张冠李戴了？）
        // System.out.println(subStr.substring(-5));
        // 报错：java.lang.StringIndexOutOfBoundsException: String index out of range: -5
        System.out.println(subStr.substring(1, 1));
        System.out.println(subStr.substring(0, 10));
    }

    /**
     * 字符串反转
     */
    @Test
    public void testStrRevi() {
        String str = "ABCDEF";
        // String newStr = new StringBuilder(str).reverse().toString();
        String newStr = reverse(str);
        System.out.println(newStr);
    }

    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    /**
     * 编写一种方法来检查输入的String是否为回文？
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.reverse();
        return strBuilder.toString().equals(str);
    }

    public static boolean isPalindromeString(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        System.out.println(length);
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testPalindrome() {
        String str = "好吗好";
        // boolean b = isPalindrome(str);
        boolean b = isPalindromeString(str);

        System.out.println(b);
    }

    /**
     * 写一个方法来从字符串中删除给定的字符？
     */
    public static String removeChar(String str, char c) {
        if (str == null) {
            return null;
        }
        return str.replaceAll(Character.toString(c), "");
    }

    /**
     * 编写一个程序来打印String的所有排列？
     *
     * 排列是有序的字符列表的元素的重新排列，使得每个排列相对于其他排列是唯一的。 例如下面是字符串“ABC”的排列 – ABC ACB BAC BCA CBA CAB。
     */
    public static Set<String> getPermutations(String string) {
        // All permutations
        Set<String> permutationsSet = new HashSet<String>();
        // invalid strings
        if (string == null || string.length() == 0) {
            permutationsSet.add("");
        } else {
            // First character in String
            char initial = string.charAt(0);
            // Full string without first character
            String rem = string.substring(1);
            // Recursive call
            Set<String> wordSet = getPermutations(rem);
            for (String word : wordSet) {
                for (int i = 0; i <= word.length(); i++) {
                    permutationsSet.add(charInsertAt(word, initial, i));
                }
            }
        }
        return permutationsSet;
    }

    public static String charInsertAt(String str, char c, int position) {
        String begin = str.substring(0, position);
        String end = str.substring(position);
        return begin + c + end;
    }

    @Test
    public void testPermutation() {
        System.out.println(getPermutations("ABC"));
        // Prints
        // [ACB, BCA, ABC, CBA, BAC, CAB]
    }

    @Test
    public void testSplit() {
        String scopeCodes = "";
        String codeArr[] = scopeCodes.split(",");
        String code = "";
        if (codeArr.length > 1) {
            code = codeArr[0];
        } else if (codeArr.length == 1) {
            code = scopeCodes;
        } else {
            // TODO...
        }
        System.out.println(code);
    }

    @Test
    public void testArr() {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        List<Integer> list3 = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(5);
            }
        };

        List<Integer> list4 = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
            }
        };

        List<Integer> list5 = new ArrayList<Integer>();

        boolean bool = list1.retainAll(list2);
        System.out.println(bool);
        System.out.println(list1.toString());

        // boolean b2 = list3.retainAll(list2);
        // System.out.println(b2);
        // System.out.println(list3.toString());

        boolean b3 = list3.retainAll(list5);
        System.out.println(b3);
        System.out.println(list3.size());
    }

    @Test
    public void testRemoveStr() {
        String s = "";
        System.out.println(s.substring(0));

        System.out.println(!StringUtils.isEmpty(s));
    }

    @Test
    public void testSwitch() { // 出现NullPointerException
        String type = null;

        switch (type) {
            case "1":
                System.out.println(type);
                break;
            default:
                System.out.println("null");
        }
    }

    @Test
    public void testList() {
        List<String> ACTIVITY_DAYS = Arrays.asList("2020-02-27", "2020-02-28", "2020-02-29", "2020-03-01", "2020-03-02",
                "2020-03-03", "2020-03-06", "2020-03-07", "2020-03-08", "2020-03-09");

        System.out.println(ACTIVITY_DAYS.contains("2020-03-08"));

        String s = " ";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(StringUtils.isEmpty(s));
    }

    @Test
    public void testLongAndLatitude() {
        double lng = 120.7369183;
        double lat = 31.25638333;

        int dist = 200000; // 距离范围

        double lng1 = lng - dist / Math.abs(Math.cos(lat * Math.PI / 180) * 111044.736);
        double lng2 = lng + dist / Math.abs(Math.cos(lat * Math.PI / 180) * 111044.736);
        double lat1 = lat - (dist / 111044.736);
        double lat2 = lat + (dist / 111044.736);
        System.out.println(lng1);
        System.out.println(lng2);
        System.out.println(lat1);
        System.out.println(lat2);

        double lng11 = lng - dist / Math.abs(Math.cos(Math.toRadians(lat)) * 111044.736);
        double lng22 = lng + dist / Math.abs(Math.cos(Math.toRadians(lat)) * 111044.736);
        double lat11 = lat - (dist / 111044.736);
        double lat22 = lat + (dist / 111044.736);
        System.out.println(lng11);
        System.out.println(lng22);
        System.out.println(lat11);
        System.out.println(lat22);
    }

    private Date getNextNDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + n);
        return calendar.getTime();
    }

    @Test
    public void test11() {
        System.out.println(getNextNDate(new Date(), 0));
    }

    public String getLocalhost() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String localname = ia.getHostAddress();
            return localname;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testIp() {
        System.out.println(getLocalhost());
    }

    @Test
    public void testCompareStr() {
        String a = "300";
        String b = "1000";

        System.out.println(b.compareTo(a));
    }

    @Test
    public void testIdcard() { // 身份证判定性别
        String idcard = "413026198709172";

        int sex = 1;
        if (idcard.length() == 15 && Integer.parseInt(idcard.substring(14, 15)) % 2 != 1) {
            sex = 2;
        }
        if (idcard.length() == 18 && Integer.parseInt(idcard.substring(16, 17)) % 2 != 1) {
            sex = 2;
        }
        System.out.println(sex);

        System.out.println(File.separator);
    }

    @Test
    public void testEncode() {
        String resp = "277120$$$$18652420434$$$$2020/5/7 16:18:42$$$$³É¹¦$$$$DELIVRD||||277120$$$$15962143194$$$$2020/5/7 16:25:18$$$$Ê§°Ü$$$$WX-FAIL||||277120$$$$17096851003$$$$2020/5/7 16:18:42$$$$³É¹¦$$$$DELIVRD||||277120$$$$15250080297$$$$2020/5/7 16:25:18$$$$Ê§°Ü$$$$WX-FAIL";
        String result = null;
        try {
            result = new String(resp.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {

        }

        String[] strArr = result.split("\\|\\|\\|\\|");
        for (String record : strArr) {
            if (record.length() > 0) {
                System.out.println(record);
                // String[] detail = record.split("$$$$");
                // System.out.println(JSONObject.toJSONString(detail));
            }

        }
    }

    public boolean empty(Object s) {
        if (s == null || s.toString().trim().equals("")) {
            return true;
        }
        return false;

    }

    @Test
    public void testList1() {
        List<String> list = new ArrayList<>();
        System.out.println(empty(list));

        System.out.println(CollectionUtils.isEmpty(list));

        System.out.println("8007".replaceFirst("E:0", ""));
    }

    @Test
    public void testHashcode(){
        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1：%d | str2：%d",  str1.hashCode(),str2.hashCode()));
        System.out.println(str1.equals(str2));
    }
}
