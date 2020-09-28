package cn.jasonhu.learn.TestCase;

import java.io.Serializable;

//1、定义
//序列化：把对象转换为字节序列的过程称为对象的序列化。
//反序列化：把字节序列恢复为对象的过程称为对象的反序列化

//2、对象的序列化主要有两种用途：
//1）把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；
//2）在网络上传送对象的字节序列。

//3、显式地定义serialVersionUID有两种用途：
//1）在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
//2）在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID

//Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
//在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
//如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。(InvalidCastException)

public class SerializableClass implements Serializable {
    private static final long serialVersionUID = -7020619477594468968L;
}

//产生序列号
//cd target/classes && serialver SerializableClass