package cn.jasonhu.learn.TestCase.java8Feature;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {

    private Integer id;
    private String name;
    private Integer age;

    public static List<Person> getList() {
        return Arrays.asList(
                new Person(1, "1号", 19),
                new Person(2, "2号", 16),
                new Person(2, "2号", 16),
                new Person(3, "3号", 20),
                new Person(4, "4号", 19),
                new Person(5, "5号", 20)
        );
    }
}


/**
 * java8 新特性
 */
public class TestJava8Feature {

    /**
     * Lambda表达式: 由3部分组成：1.参数 2.-> 3.函数主体, 例如：
     */
    @Test
    public void test1() {
        // 正常写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("do it...");
            }
        }).start();

        // example1 Lambda表达式写法
        new Thread(() -> System.out.println("do it...")).start();

        // example2
        List<String> list = Arrays.asList("Hello", "World", "Java");
        list.forEach(str -> System.out.println(str));
        // list.forEach(System.out::println); //方法引用
    }

    /**
     * 方法引用
     */
    @Test
    public void test2() {
        List<Person> list = Person.getList();
        list.sort(Comparator.comparingInt(Person::getAge));
        list.forEach(System.out::println);
    }

    /**
     * stream用法
     */
    @Test
    public void test3() {
        List<Person> list = Person.getList();

        // 求和
        int totalAge = list.stream().mapToInt(Person::getAge).sum();
        System.out.println("totalAge=" + totalAge);

        // 排序：reverseOrder降序。默认升序
        List<Person> sortedList = list.stream()
                .sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder())
                        .thenComparing(Person::getId))
                .collect(Collectors.toList());
        System.out.println("sortedList=" + sortedList);

        // 转map, (oldValue, newValue) -> oldValue作为第三个参数，解决重复key的问题，即如果key是重复的，选择oldKey or newKey
        Map<Integer, String> map1 = list.stream()
                .collect(Collectors
                        .toMap(Person::getId, Person::getName, (oldValue, newValue) -> oldValue));
        Map<Integer, String> map2 = list.stream()
                .collect(Collectors
                        .toMap(x -> x.getId(), x -> x.getName(), (oldValue, newValue) -> oldValue));
        System.out.println("map1=" + map1);
        System.out.println("map2=" + map2);

        // 过滤
        List<Person> filterList = list.stream().filter(x -> x.getAge() == 20)
                .collect(Collectors.toList());
        System.out.println("filterList=" + filterList);

        // 去重
        List<Person> distinctList = list.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctList=" + distinctList);

        // 分页
        List<Person> shareList = list.stream().skip(1).limit(3).collect(Collectors.toList());
        System.out.println("shareList=" + shareList);

        //min / max
        list.stream().min(Comparator.comparingInt(Person::getAge)).ifPresent(System.out::println);
        list.stream().max(Comparator.comparingInt(Person::getAge)).ifPresent(System.out::println);

        //anyMatch/allMatch/noneMatch
        if (list.stream().noneMatch(person -> person.getAge() > 20)) {
            System.out.println("没有人年龄大于20岁");
        }
        if (list.stream().anyMatch(person -> person.getAge() > 15)) {
            System.out.println("存在年龄大于15岁的人");
        }
        if (list.stream().allMatch(person -> person.getAge() > 10)) {
            System.out.println("所有人年龄都大于10岁");
        }
    }

    /**
     * Optional类, 为了解决返回值为null的问题
     */
    @Test
    public void test4() {
        Person p = new Person();
        Optional<Person> p1 = Optional.ofNullable(p);
        p1.ifPresent(person -> {
            System.out.println(person);
        });
    }


}
