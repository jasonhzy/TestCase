package cn.jasonhu.learn.TestCase.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestMap {

    @Test
    public void testMap() {
        Map<String, String> a = new HashMap<>();

        Map<String, String> b = new ConcurrentHashMap<>();
        b.put("a", "b");
    }
}
