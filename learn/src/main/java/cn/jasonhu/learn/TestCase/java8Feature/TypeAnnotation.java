package cn.jasonhu.learn.TestCase.java8Feature;

import java.lang.annotation.*;

/**
 * 注解（也被称做元数据），Java 8 主要有两点改进：类型注解和重复注解。
 *
 * 1、在java 8之前，注解只能是在声明的地方所使用，比如类，方法，属性；java 8里面，注解可以应用在任何地方
 * 2、类型注解只是语法而不是语义，并不会影响java的编译时间，加载时间，以及运行时间，也就是说，编译成class文件的时候并不包含类型注解。
 * 3、类型注解被用来支持在Java的程序中做强类型检查
 *
 */
public class TypeAnnotation {

    //自定义注解
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)  //注解的核心
    public @interface Filter {
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }
}
