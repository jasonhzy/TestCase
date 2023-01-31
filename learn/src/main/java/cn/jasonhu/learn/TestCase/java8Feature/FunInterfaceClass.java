package cn.jasonhu.learn.TestCase.java8Feature;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 函数式接口定义： 1、一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。 2、函数式接口可以被隐式转换为 lambda 表达式。（Lambda
 * 表达式和方法引用（实际上也可认为是Lambda表达式）上） 3、在任意函数式接口上使用@FunctionalInterface注解，这样做可以检查它是否是一个函数式接口（即使一个接口没有标注@FunctionalInterface，如果这个接口满足函数式接口规则，依旧被当作函数式接口）
 */
public class FunInterfaceClass {

    @Test
    public void test1() {
        // 传统的写法
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        });
        t1.start();

        // 函数式接口写法
        Thread t2 = new Thread(() -> System.out.println("t2"));
        t2.start();

        // Runnable的源码
        // @FunctionalInterface
        // public interface Runnable {
        // public abstract void run();
        // }
    }

    // 内置函数式接口：Consumer<T> 消费型接口
    public static void consume(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        consume(10000, (m) -> {
            System.out.println("今日全场8折");
            System.out.println("顾客消费：" + (m * 0.8) + "元");
        });
    }

    // 内置函数式接口：Supplier<T> 供给型接口
    // 生成num个整数,并存入集合
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    @Test
    public void test3() {
        // 10个100以内的随机数
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    // 内置函数式接口：Function<T, R> 函数型接口，常用于数据的处理转换
    public static <T, R> List<R> map(List<T> list, Function<T, R> fun) {
        List<R> returnList = new ArrayList<>(list.size());
        for (T e : list) {
            returnList.add(fun.apply(e));
        }
        return returnList;
    }

    @Test
    public void test4() {
        List<Employee> employees = Arrays.asList(
                new Employee("老张"),
                new Employee("小李"),
                new Employee("老王"),
                new Employee("小刘"),
                new Employee("小胖")
        );
        List<String> nameList = map(employees, (employee -> employee.getName()));
        System.out.println(nameList); // 输出：[老张, 小李, 老王, 小刘, 小胖]
    }

    // 内置函数式接口：Predicate<T> 断言型接口
    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }

    @Test
    public void test5() {
        List<Employee> employees = Arrays.asList(new Employee("老张"),
                new Employee("小李", 3000.00),
                new Employee("老王", 5000.00),
                new Employee("小刘", 7000.00),
                new Employee("小胖", 10000.00));
        //过滤薪资小于5000的员工
        List<Employee> filter = filter(employees,
                employee -> employee.getSalary() > 5000.00);
        for (Employee employee : filter) {
            System.out.println(employee.getName() + ":" + employee.getSalary());
        }
    }


    // 方法引用
    @Test
    public void test6() {
        // 静态方法
        BinaryOperator<Double> binaryOperator = (x, y) -> Math.pow(x, y);
        // 等价于
        BinaryOperator<Double> binaryOperator1 = Math::pow;

        // 实例方法: 类::实例方法
        Function<Employee, String> f = (Employee e) -> e.getName();
        // 等价于
        Function<Employee, String> f1 = Employee::getName;

        // 构造方法引用
        Function<String, Employee> f2 = (name) -> new Employee(name);
        // 等价于
        Function<String, Employee> f3 = Employee::new;

        // ---------------------------------------------------------
        // 对象::实例方法
        Employee e = new Employee("小李", 3000.00);
        Supplier<String> s = () -> e.getName();
        // 等价于
        Supplier<String> s1 = e::getName;
        System.out.println(s.get());
        System.out.println(s1.get());
    }

}

@Data
class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
