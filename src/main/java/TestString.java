import java.util.HashSet;
import java.util.Set;

public class TestString {

    private static void testStr() {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = "ab" + "c";

        System.out.println("TestInterruptRunnable == b?" + (a == b));
        System.out.println("TestInterruptRunnable == c?" + (a == c));
        System.out.println("TestInterruptRunnable == d?" + (a == d));


        System.out.println("b == c?" + (b == c));
        System.out.println("b == d?" + (b == d));
        System.out.println("c == d?" + (c == d));
    }

    private static void testString() {
        HashSet<StringBuilder> hs = new HashSet<StringBuilder>();
        StringBuilder sb1 = new StringBuilder("aaa");
        StringBuilder sb2 = new StringBuilder("aaabbb");
        hs.add(sb1);
        hs.add(sb2);    //这时候HashSet里是{"aaa","aaabbb"}

        StringBuilder sb3 = sb1;
        sb3.append("bbb");  //这时候HashSet里是{"aaabbb","aaabbb"}
        System.out.println(hs);
    }

    private static void testSet() {
        Set set = new HashSet<String>();
        set.add("1");
        set.add("1");
        System.out.println(set);
    }


    public static void test1() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); //false
        System.out.println(s1 == s5); //true
        //字符串的+操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder
        // 对象用toString方法处理成String对象
        //可以用javap -c TestString.class命令获得class文件对应的JVM字节码指令就可以看出来
        System.out.println(s1 == s6); //false
        System.out.println(s1 == s6.intern()); //true
        System.out.println(s2 == s2.intern()); //false
    }

    public static void main(String[] args) {
//        testString();
//        testSet();
        test1();
//        testStr();
//        String a = "abc";
//        String b = "abc";
//        System.out.println(a.hashCode() + "---" + b.hashCode());
//
//        StringBuilder sb1=new StringBuilder("1");
//        StringBuilder sb2=new StringBuilder("1");
//        System.out.println(sb1.hashCode() + "===" + sb2.hashCode());
//        if(sb1.equals(sb2)){
//            System.out.println("equal");
//        }else{
//            System.out.println("not equal");
//        }

    }
}
