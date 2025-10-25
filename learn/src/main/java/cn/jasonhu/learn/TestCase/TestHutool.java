package cn.jasonhu.learn.TestCase;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.apache.commons.codec.digest.DigestUtils;

public class TestHutool {

    public static void main(String[] args) {
        // Entity student = Entity.create("student").set("username", "jasonhu").set("age", 30);
        // try{
        //     Db.use().insert(student);
        // }catch (Exception e) {
        //     System.out.println(e);
        // }

        // appId=8umnqtv&nonce=1730333188655&sign=4e0e97bdda8af5c9d543f13ee24f6c13a89a0622
        String appId = "8umnqtv";
        String nonce = "1730333188655";
        String sign = "4e0e97bdda8af5c9d543f13ee24f6c13a89a0622";
        String secret = "2789e25f5cc21a9934238f018a39c7fa4014f845";

        String str = appId + nonce + secret;
        String realSign = DigestUtils.sha1Hex(str);
        if (realSign.equalsIgnoreCase(sign)) {
            System.out.println("ok");
        }

    }
}
