package cn.jasonhu.learn.TestCase.collections;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestArray {

    // Arrays.asList()是泛型方法，传递的数组必须是对象数组，而不是基本类型
    @Test
    public void test() {
        int[] myArray = {1, 2, 3};
        // 使用包装类型数组就可以解决这个问题：Integer[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(JSONObject.toJSONString(myList));
        System.out.println(myList.size()); //1
        System.out.println(myList.get(0)); //数组地址值
        System.out.println(myList.get(1)); //报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]); //1
    }

    // 2、使用集合的修改方法: add()、remove()、clear()会抛出异常
    @Test
    public void test1() {
        List myList = Arrays.asList(1, 2, 3);
        myList.add(4);//运行时报错：UnsupportedOperationException
        myList.remove(1);//运行时报错：UnsupportedOperationException
        myList.clear();//运行时报错：UnsupportedOperationException
    }

}
