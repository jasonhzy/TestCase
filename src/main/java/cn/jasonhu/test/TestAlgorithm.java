package cn.jasonhu.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestAlgorithm {

    /**
     * 计算n的阶乘，即：1 * 2 * 3 * ..... * n
     */
    private long factorial(int n){
        if(n == 1){
            return 1;
        }
        return n * factorial(n-1);
    }

    @Test
    public void testFactorial(){
        System.out.println(factorial(10));
    }

    /**
     * 题目：写一个函数，输入n，求斐波那契数列的第n项。
     * 　　　　　　　　　　　　　　　　　 0,                n=1
     * 斐波那契数列定义如下：f(n)=      1,                n=2
     *                               f(n-1)+f(n-2),    n>2
     * 例子：0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ... 
     */
    private int fibonacci(int n){
        if(n == 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public int fibonacci1(int count){
        int n1 = 0, n2 = 1, n3 = 0, i;

        for(i = 0; i < count; i++){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            if(i == count - 3){
                return n3;
            }
        }
        return n3;
    }

    @Test
    public void testFibonacci() {
        int a = fibonacci(10);
//        int a = fibonacci1(10);
        System.out.println(a);
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     *  给定 nums = [11, 7, 15, 2], target = 9
     *  因为 nums[0] + nums[1] = 2 + 7 = 9
     *  所以返回 [0, 1]
     */

    private int[] twoSum(int[] nums, int target) {
        int c[] = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                c[0] = i;
                c[1] = map.get(nums[i]);
            }
            map.put(target - nums[i], i);
        }
        return c;
    }

    @Test
    public void test(){
        //int a[] = {7, 11, 2, 15};
        int a[] = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum(a, 6)));
    }

    /**
     * N级台阶（比如100级），每次可走1步或者2步，求总共有多少种走法？
     */
    @Test
    public void testFibonacci2(){
        int n = 12;
        System.out.println(fibonacci(n));;
    }
}
