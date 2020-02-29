import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;

public class TestString {
    @Test
    public void testString() {
        HashSet<StringBuilder> hs = new HashSet<StringBuilder>();
        StringBuilder sb1 = new StringBuilder("aaa");
        StringBuilder sb2 = new StringBuilder("aaabbb");
        hs.add(sb1);
        hs.add(sb2);    //这时候HashSet里是{"aaa","aaabbb"}

        StringBuilder sb3 = sb1;
        sb3.append("bbb");  //这时候HashSet里是{"aaabbb","aaabbb"}
        System.out.println(hs);
    }

    @Test
    public void testSet() {
        Set set = new HashSet<String>();
        set.add("1");
        set.add("1");
        System.out.println(set);
    }

    @Test
    public void test1() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        String s7 = new String("Programming");
        String s8 = s3.concat(s4);
        String s9 = s1.concat("");
        String s10 = "Program" + new String("ming");
        System.out.println(s1 == s2); //false
        System.out.println(s2 == s2.intern()); //false

        System.out.println(s1 == s5); //true
        //字符串的+操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder
        // 对象用toString方法处理成String对象
        //可以用javap -c TestString.class命令获得class文件对应的JVM字节码指令就可以看出来
        System.out.println(s1 == s6); //false
        System.out.println(s1 == s6.intern()); //true
        System.out.println(s2 == s7); //false
        System.out.println(s1 == s8); //false
        System.out.println(s1 == s9); //true
        System.out.println(s1 == s10); //false
    }

//    String 字符串常量，不可变的，线程安全
//    StringBuffer 字符串变量（线程安全）
//    StringBuilder 字符串变量（非线程安全）
//    StringBuffer和StringBuilder，有公共父类AbstractStringBuilder(抽象类)，区别StringBuffer方法上加了synchronized

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
//        为初始化，编译不通过。原因：成员变量有初始值，而局部变量没有初始值
//        String s;
//        System.out.println("s="+s);
    }

    @Test
    public void testSubstring() {
        String subStr = "123456789";
        //示例1、正常情况：由下面两个例子可以发现下标是从1而不是0开始的
        System.out.println(subStr.substring(0));//输出:123456789
        System.out.println(subStr.substring(1));//输出:23456789
        System.out.println(subStr.substring(subStr.length()));//输出空符串

        //示例2、字符超长（直接报错）
        //System.out.println(subStr.substring(subStr.length()+1));
        //报错：java.lang.StringIndexOutOfBoundsException: String index out of range: -1

        //示例3、输入负数（直接报错，可我怎么听说是反着截取出来的呢，难道是我张冠李戴了？）
        //System.out.println(subStr.substring(-5));
        //报错：java.lang.StringIndexOutOfBoundsException: String index out of range: -5
        System.out.println(subStr.substring(1, 1));
        System.out.println(subStr.substring(0, 10));
    }

    /**
     * 字符串反转
     */
    @Test
    public void testStrRevi() {
        String str = "ABCDEF";
        //String newStr = new StringBuilder(str).reverse().toString();
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
    public void testPalindrome(){
        String str = "好吗好";
        //boolean b = isPalindrome(str);
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
     * 排列是有序的字符列表的元素的重新排列，使得每个排列相对于其他排列是唯一的。
     * 例如下面是字符串“ABC”的排列 – ABC ACB BAC BCA CBA CAB。
     */
    public static Set<String> getPermutations(String string)
    {
        //All permutations
        Set<String> permutationsSet = new HashSet<String>();
        // invalid strings
        if (string == null || string.length() == 0){
            permutationsSet.add("");
        } else {
            //First character in String
            char initial = string.charAt(0);
            //Full string without first character
            String rem = string.substring(1);
            //Recursive call
            Set<String> wordSet = getPermutations(rem);
            for (String word : wordSet) {
                for (int i = 0; i <= word.length(); i++) {
                    permutationsSet.add(charInsertAt(word, initial, i));
                }
            }
        }
        return permutationsSet;
    }

    public static String charInsertAt(String str, char c, int position)
    {
        String begin = str.substring(0, position);
        String end = str.substring(position);
        return begin + c + end;
    }

    @Test
    public void testPermutation()
    {
        System.out.println(getPermutations("ABC"));
        //Prints
        //[ACB, BCA, ABC, CBA, BAC, CAB]
    }

    @Test
    public void testSplit(){
        String scopeCodes = "";
        String codeArr[] = scopeCodes.split(",");
        String code = "";
        if(codeArr.length > 1){
            code = codeArr[0];
        }else if(codeArr.length == 1){
            code = scopeCodes;
        }else{
            //TODO...
        }
        System.out.println(code);
    }

    @Test
    public void testArr(){
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

//        boolean b2 = list3.retainAll(list2);
//        System.out.println(b2);
//        System.out.println(list3.toString());

        boolean b3 = list3.retainAll(list5);
        System.out.println(b3);
        System.out.println(list3.size());
    }

    @Test
    public void testRemoveStr(){
        String  s = "";
        System.out.println(s.substring(0));

        System.out.println(!StringUtils.isEmpty(s));
    }


    @Test
    public void testRemainder(){
        int  amount = 189;
        System.out.println(amount / 10);


        Long a = 1000000000000000000L;
        Integer b = 999999999;
        System.out.println(a.intValue());
        if(a < b.longValue() ){
            System.out.println("true");
        }
    }

    @Test
    public void testSwitch(){ // 出现NullPointerException
        String type = null;

        switch (type){
            case "1":
                System.out.println(type);
                break;
            default:
                System.out.println("null");
        }
    }

    @Test
    public void testList(){
        List<String> ACTIVITY_DAYS = Arrays.asList(
                "2020-02-27", "2020-02-28", "2020-02-29", "2020-03-01", "2020-03-02", "2020-03-03",
                "2020-03-06", "2020-03-07", "2020-03-08", "2020-03-09");

        System.out.println(ACTIVITY_DAYS.contains("2020-03-08"));

        String s = " ";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(StringUtils.isEmpty(s));
    }
}
