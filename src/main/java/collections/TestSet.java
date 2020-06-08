package collections;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestSet {
    @Test
    public void test1() {
        Set<StringBuilder> hs = new HashSet<StringBuilder>();
        StringBuilder sb1 = new StringBuilder("aaa");
        StringBuilder sb2 = new StringBuilder("aaabbb");
        hs.add(sb1);
        hs.add(sb2); // 这时候HashSet里是{"aaa","aaabbb"}
        System.out.println(hs);

        StringBuilder sb3 = sb1;
        sb3.append("bbb"); // 这时候HashSet里是{"aaabbb","aaabbb"}
        System.out.println(hs);
    }

    @Test
    public void test2() {
        Set set = new HashSet<String>();
        set.add("1");
        set.add("1");
        System.out.println(set);

        Set<Integer> setA = new HashSet<Integer>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);

        Set<Integer> setB = new HashSet<Integer>();
        setB.add(3);
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setA.removeAll(setB);
        System.out.println(setA);
    }

    @Test
    public void test3() {
        Set set = new HashSet<String>();

        String str = new String("test");
        set.add(str);
        set.add(str);
        System.out.println(set);
    }
}
