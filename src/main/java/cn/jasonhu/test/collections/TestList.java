package cn.jasonhu.test.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestList {

    List<String> list = new ArrayList() {
        {
            add("aa");
            add("bb");
            add("cc");
        }
    };

    String strArr[] = new String[]{"aa", "bb", "cc"};

    //List转换成为数组：调用ArrayList的toArray方法
    @Test
    public void test1() {
        String arr[] = list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));
    }

    //数组转换成为List：调用Arrays的asList方法
    @Test
    public void test2(){
        //推荐方式
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(strArr));
        arrayList.add("dd");
        System.out.println(arrayList);

        //使用了asList()方法。这不是最好的，因为asList()返回的列表的大小是固定的。事实上，返回的列表不是java.util.ArrayList，而是定义在java.util.Arrays中一个私有静态类。
        // 我们知道ArrayList的实现本质上是一个数组，而asList()返回的列表是由原始数组支持的固定大小的列表。这种情况下，如果添加或删除列表中的元素，
        // 程序会抛出异常UnsupportedOperationException。
        List<String> list = Arrays.asList(strArr);
        list.add("dd"); //抛出异常UnsupportedOperationException
        System.out.println(list);
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item); // 出现异常： ConcurrentModificationException
            }
        }

        System.out.println(list);
    }

}
