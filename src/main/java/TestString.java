import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
    }

//    String 字符串常量，不可变的，线程安全
//    StringBuffer 字符串变量（线程安全）
//    StringBuilder 字符串变量（非线程安全）
// StringBuffer和StringBuilder，有公共父类AbstractStringBuilder(抽象类)，区别StringBuffer方法上加了synchronized

    /**
     * 字符串反转
     */
    @Test
    public void testStrRevi(){
        String str = "ABCDEF";
        //String newStr = new StringBuilder(str).reverse().toString();
        String newStr = reverse(str);
        System.out.println(newStr);
    }

    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1){
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
}
