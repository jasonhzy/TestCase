package cn.jasonhu.learn.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex {

    @Test
    public void test1() {
        String type = "111";
        if (Pattern.matches("^[\\dA-Za-z_]+$", type)) {
            System.out.println("1");
        }

        if (Pattern.matches("^[\\d_]+$", type)) {
            System.out.println("2");
        }

        if (Pattern.matches("^[^.][\\d\\.]+[^.]$", type)) {
            System.out.println("3");
        }

        if (Pattern.matches("^(?!\\.)([\\d\\.])+(?<!\\.)$", type)) {
            System.out.println("4");
        }

        if (Pattern.compile("#[a-zA-Z]+#").matcher("测试#nickname#").find()) {
            System.out.println("5");
        }

        System.out.println("测试#nickname#, #nickname#你好".replace("#nickname#", "cn/jasonhu/learn"));
    }

    @Test
    public void test2() {
        // 1)先行断言 (?=exp)//表示匹配表达式前面的位置
        // 注意：先行断言的执行步骤是这样的先从要匹配的字符串中的最右端找到第一个 ing
        // (也就是先行断言中的表达式)然后 再匹配其前面的表达式，若无法匹配则继续查找第二个ing 再匹配第二个ing
        // 前面的字符串，若能匹配则匹配，符合正则的贪婪性。
        // 例如： .*(?=ing) 可以匹配 "cooking singing" 中的 "cooking sing" 而不是cook
        String str = "cooking singing";
        Pattern p = Pattern.compile(".*(?=ing)");
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }
        // 2）后发断言 (?<=exp) //表示匹配表达式后面的位置
        // 例如(?<=abc).* 可以匹配 abcdefg 中的 defg
    }

    @Test
    public void test3() {
        String pri = "insert into table （字段1，字段2） values ('1',''a','b'')";
        pri = pri.replaceAll("'('.+')", "\"$1").replaceAll("('.+')'", "$1\"");
        System.out.println(pri);
    }
}
