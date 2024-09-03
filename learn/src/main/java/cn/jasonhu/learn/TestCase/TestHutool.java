package cn.jasonhu.learn.TestCase;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

public class TestHutool {

    public static void main(String[] args) {
        Entity student = Entity.create("student").set("username", "jasonhu").set("age", 30);
        try{
            Db.use().insert(student);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
