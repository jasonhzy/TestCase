package cn.jasonhu.learn.TestCase.dataType;

import cn.jasonhu.commons.utils.MD5Util;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
        String s6 = s3
                + s4; // jad 反编译结果即：String s6 = (new StringBuilder()).append(s3).append(s4).toString();
        String s7 = new String("Programming");
        String s8 = s3.concat(s4);
        String s9 = s1.concat("");
        String s10 = "Program" + new String(
                "ming"); //jad 反编译结果即：String s10 = (new StringBuilder()).append("Program").append(new String("ming")).toString();

        final String s11 = "Program";
        final String s12 = "ming";
        String s13 = s11 + s12;

        System.out.println(s1 == s4);

        System.out.println(s1 == s2); // false
        System.out.println(s2 == s2.intern()); // false

        System.out.println(s1 == s5); // true
        // 字符串的+ 或者 +=操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder
        // 对象用toString方法处理成String对象
        // 可以用javap -c TestString.class命令获得class文件对应的JVM字节码指令就可以看出来
        //参考：https://droidyue.com/blog/2014/08/30/java-details-string-concatenation/
        System.out.println(s1 == s6); // false

        //调用intern后，首先检查字符串常量池中是否有该对象的引用，如果存在，，则将这个引用返回给变量，否则将引用加入并返回给变量。
        System.out.println(s1 == s6.intern()); // true
        System.out.println(s2 == s7); // false

        //contact方法源码
        System.out.println(s1 == s8); // false
        System.out.println(s1 == s9); // true
        System.out.println(s1 == s10); // false

        // 对于final字段，编译期直接进行了常量替换（而对于非final字段则是在运行期进行赋值处理的）
        System.out.println("s1 == s13 ? " + (s1 == s13)); // true
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
        // 示例1、正常情况：由下面两个例子可以发现下标是从0不是1开始的
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
     * <p>
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
            System.out.print("initial = " + initial + " ");
            // Full string without first character
            String rem = string.substring(1);
            System.out.println("rem = " + rem);
            // Recursive call
            Set<String> wordSet = getPermutations(rem);
            System.out.println("wordSet===" + wordSet);
            for (String word : wordSet) {
                System.out.println("word = " + word + "|");
                for (int i = 0; i <= word.length(); i++) {
                    permutationsSet.add(charInsertAt(word, initial, i));
                }
            }
            System.out.println();
        }
        return permutationsSet;
    }

    public static String charInsertAt(String str, char c, int position) {
        String begin = str.substring(0, position);
        String end = str.substring(position);
        System.out.println("begin=" + begin + " end=" + end + " mid=" + c );
        return begin + c + end;
    }

    @Test
    public void testPermutation() {
        //System.out.println(getPermutations("ABC"));
        System.out.println(getPermutations("OLL"));
        // Prints
        // [ACB, BCA, ABC, CBA, BAC, CAB]
    }

    @Test
    public void testSplit() {
        String scopeCodes = "1,";
        String codeArr[] = scopeCodes.split(",");
        String code = "";
        if (codeArr.length > 1) {
            code = codeArr[1];
            System.out.println(">1==>" + code);
            System.out.println(JSON.toJSONString(codeArr));
        } else if (codeArr.length == 1) {
            code = codeArr[0];
            System.out.println("1==>" + code);
            System.out.println(JSON.toJSONString(codeArr));
        } else {
            // TODO...
            System.out.println();
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

        try {
            switch (type) {
                case "1":
                    System.out.println(type);
                    break;
                default:
                    System.out.println("null");
            }
        } catch (Exception e) {
            System.out.println("===>>>" + e.getMessage());
        }

        String str = "hello ";
        switch (1) {
            default:
                str += "world";
            case 1:
                str += "1 !!!";
                System.out.println(str);
                break;
            case 2:
                str += "2 !!!";
                System.out.println(str);
                break;
        }


    }

    @Test
    public void testList() {
        List<String> ACTIVITY_DAYS = Arrays
                .asList("2020-02-27", "2020-02-28", "2020-02-29", "2020-03-01", "2020-03-02",
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
    public void testHashcode() {
        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1：%d | str2：%d", str1.hashCode(), str2.hashCode()));
        System.out.println(str1.equals(str2));
    }

    @Test
    public void testH24MM() {
        String time = "2020-11-10 21:10";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            System.out.println(format.parse(time));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);

    }


    @Test
    public void testSplitStr() {
        String str = " 1,3,3";
        String[] arr = str.split(",");
        System.out.println("==>" + arr.length);

        List<Long> ids = Arrays.stream(arr).distinct()
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(ids));
    }

    @Test
    public void testMd5() {
//        String str = "111111";
//        String key = "72aaa27d";

        String str = "111111";
        String key = "9ed515e5";

        String md5 = MD5Util.MD5(MD5Util.MD5(str) + key);
        System.out.println(md5);
    }


    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    @Test
    public void testStringFormat() {
        String str = String.format("%02x", 1000);
        System.out.println(str);

        //获取系统信息
        String systemType = System.getProperty("os.name");
        System.out.println(systemType);

        String path = System.getProperty("user.dir");
        System.out.println(path);

        String str1 = String
                .format("A3%02X-%08X-%08X-%08X-%08X", 255, 999999999, 999999999, 999999999,
                        999999999);
        System.out.println(str1);

        Integer a = Integer.valueOf("11111111", 2);
        System.out.println(a);

        Integer b = Integer.parseInt("11111111", 2);
        System.out.println(b);

        String license = "669100ED-7415-A308-000001-000001-BB03789128F6";
        String[] detail = license.split("-");
        String ver = detail[2].substring(0, 2);
        switch (ver) {
            case "A3":
                String strMoulds = detail[2].substring(2, 4);
                String str2 = Integer.toBinaryString(Integer.parseInt(strMoulds, 16));

                String strModules = addZeroForNum(str2, 8);
                System.out.println(strModules);

                for (int i = 0; i < strModules.length(); i++) {
                    char ch = strModules.charAt(i);
                    if (ch == '0') {

                    }
                }
                break;
            default:
        }

    }

    @Test
    public void testJson() {
        String license = "341BBD0E-0D9D-A102-3B9AC9FF3B9AC9FF-3B9AC9FF3B9AC9FF-B9DDD89CA1F0";
        String[] detail = license.split("-");
        if (detail[3].length() != 16 || detail[4].length() != 16) {

        }
        // DEVICE->MOULD->CLAMP->SPARE
        String deviceHexadecimal = detail[3].substring(0, 8);
        BigInteger device = new BigInteger(deviceHexadecimal, 16);

        String mouldHexadecimal = detail[3].substring(8, 16);
        BigInteger mould = new BigInteger(deviceHexadecimal, 16);
        System.out.println(mould.intValue());

        String clampHexadecimal = detail[4].substring(0, 8);
        BigInteger clamp = new BigInteger(deviceHexadecimal, 16);
        System.out.println(clamp.intValue());

        String spareHexadecimal = detail[4].substring(8, 16);
        BigInteger spare = new BigInteger(deviceHexadecimal, 16);
        System.out.println(spare.intValue());
    }

    @Test
    public void testIntern(){
        // 当通过语句str.intern()调用intern()方法后，JVM 就会在当前类的常量池中查找是否存在与str等值的String，
        // 若存在则直接返回常量池中相应Strnig的引用；若不存在，则会在常量池中创建一个等值的String，
        // 然后返回这个String在常量池中的引用。
        //String str1 = new String("SEU") + new String("Calvin");
        //System.out.println(str1.intern() == str1);
        //System.out.println(str1 == "SEUCalvin");

        String str2 = "SEUCalvin";//新加的一行代码，其余不变
        String str1 = new String("SEU")+ new String("Calvin");
        System.out.println(str1.intern() == str1); // false
        System.out.println(str1 == "SEUCalvin"); // false
    }

    @Test
    public void test(){
        System.out.println(42 == 42.0);

        System.out.println("ABC".substring(0,0));
    }
}

//String 真正不可变有下面几点原因：
//1、保存字符串的数组被 final 修饰且为私有的，并且String 类没有提供/暴露修改这个字符串的方法。
//2、String 类被 final 修饰导致其不能被继承，进而避免了子类破坏 String 不可变。
