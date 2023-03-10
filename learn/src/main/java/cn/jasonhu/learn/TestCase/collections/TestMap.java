package cn.jasonhu.learn.TestCase.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestMap {

    @Test
    public void testMap() {
        Map<String, String> a = new HashMap<>();

        Map<String, String> b = new ConcurrentHashMap<>();
        b.put("a", "b");
    }

    @Test
    public void testLRU() {
        // 定义最大容量为10
        final int maxSize = 10;

        // 参数boolean accessOrder含义:false-按照插入顺序排序；true-按照访问顺序排序。
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(0, 0.75f, true) {
            // LinkedHashMap加入新元素时会自动调用该方法，若返回true，则会删除链表尾部的元素
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > maxSize;
            }
        };

        // 先往map中加入10个元素(定义的最大容量为10)
        for (int i = 1; i <= 10; i++) {
            map.put(i, i);
        }

        // 访问一下第6个元素,看看是否会排到链表的头部
        map.get(6);
        System.out.println("发现第6个元素排到了链表的头部：" + map.toString());

        // 再加数据
        map.put(11, 11);
        System.out.println("删除链表尾部的元素，再将新的元素添加至链表头部 ：" + map.toString());
    }

    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map);
    }

    @Test
    public void test2() {
        Hashtable hashtable = new Hashtable();
        //key value 均不能为null
        hashtable.put(null, null);
        System.out.println(hashtable);
    }
}
