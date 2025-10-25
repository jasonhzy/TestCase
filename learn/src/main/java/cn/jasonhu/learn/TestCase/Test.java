package cn.jasonhu.learn.TestCase;

import com.alibaba.fastjson.JSONObject;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class Test {

    public static void main(String[] args) {
//        System.out.println(new B().getValue());
//        testPwd();
        testPwd2();

        System.out.println(JSONObject.toJSONString(""));
    }

    static class A {

        protected int value;

        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                System.out.println("-------");
                System.out.println(this);
                System.out.println("-------");
                this.setValue(value);
                System.out.println(value);
            }
            return value;
        }
    }

    static class B extends A {

        public B() {
            super(5);
            int a = getValue() - 3;
            System.out.println("======");
            System.out.println(this);
            System.out.println("======");
            setValue(a);
        }

        @Override
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }


    public static void testPwd(){
        String password = "123456";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndDES");

//        String encryptedText = encryptor.encrypt("123456");
//        System.out.println("Encrypted Text: " + encryptedText);
        String encryptedText = "";

        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }


    private final static String PASSWORD = "123456";
    public static void testPwd2() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        // 用于设置加密密钥。密钥是用于加密和解密字符串的关键信息。
        config.setPassword(PASSWORD);
        // 加密算法的名称,jasypt-3.0.5版本后默认的加密方式
        config.setAlgorithm("PBEWithMD5AndDES");
        // 用于设置加密时迭代次数的数量，增加迭代次数可以使攻击者更难进行密码破解。
        config.setKeyObtentionIterations("1000");
        // 加密器池的大小。池是一组加密器实例，可确保加密操作的并发性。
        config.setPoolSize("1");
        // 用于设置JCE（Java Cryptography Extension）提供程序的名称。
        config.setProviderName("SunJCE");
        // 用于设置生成盐的类名称。在此配置中，我们使用了org.jasypt.salt.RandomSaltGenerator，表示使用随机生成的盐。
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        // 用于设置Jasypt使用的初始化向量（IV）生成器的类名。初始化向量是在加密过程中使用的一个固定长度的随机数，用于加密数据块，使每个数据块的加密结果都是唯一的。在此配置中，我们使用了org.jasypt.iv.RandomIvGenerator类，该类是一个随机生成器，用于生成实时随机IV的实例。这样可以确保每次加密的IV都是唯一的，从而增加加密强度。
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        // 指定加密输出类型。在此配置中，我们选择了base64输出类型。
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        // 明文1
//        String name_encrypt = "root";
//
//        // 明文加密
//        String encrypt1 = encryptor.encrypt(name_encrypt);
//        System.out.println("明文加密2：" + encrypt2);

        String encrypt1 = ""; // 密文
        // 密文解密
        String decrypt1 = encryptor.decrypt(encrypt1);
        System.out.println("密文解密1：" + decrypt1);
    }
}
