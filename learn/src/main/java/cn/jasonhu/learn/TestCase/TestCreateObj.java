package cn.jasonhu.learn.TestCase;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.UUID;

// 1、使用new关键字
// ClassA a = new ClassA();
// 使用new关键字，实际上完成了两个步骤：1、分配内存空间，创建对象；2、调用构造方法初始化对象。
// 2、反射机制
// 两种方式：
// （1）Class类的newInstance()
//当前包名为"package name"，必须使用全路径
// ClassA classA = (ClassA) Class.forName("package name.ClassA").newInstance();
// 或者
// ClassA classA = ClassA.class.newInstance();
// （2）java.lang.reflect.Constructor类的newInstance()
// 两种方式区别：
// Class.newInstance() 只能够调用无参的构造函数，即默认的构造函数；
// Constructor.newInstance() 可以根据传入的参数，调用任意构造构造函数。
//
// 事实上Class的newInstance方法内部调用Constructor的newInstance方法。
// 反射机制创建对象，使用的是类加载机制，newInstance()的特点是在调用时才创建对象，
// 通过类加载机制Class.forName("xxx").newInstance()创建对象，xxx可以从配置文件当中获取实际的值，
// 这样达到了解耦的目的，也是Spring依赖注入的原理。
// 3、clone（）方法
// 两个概念：浅拷贝和深拷贝。
//      浅拷贝：被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。
//      深拷贝：不仅要复制对象的所有非引用成员变量值，还要为引用类型的成员变量创建新的实例，并且初始化为形式参数实例值。

//      浅拷贝只是复制了对象的引用地址，两个对象指向同一个内存地址，所以修改其中任意的值，另一个值都会随之变化，这就是浅拷贝（例：assign()）
//      深拷贝是将对象及值复制过来，两个对象修改其中任意的值另一个值不会改变，这就是深拷贝
//          （例：JSON.parse()和JSON.stringify()，但是此方法无法复制函数类型）

// 浅拷贝的效果就是，对引用对象的操作，会影响到所有引用了该对象的对象
// 调用一个对象的clone方法，jvm就会创建一个新的对象，将前面对象的内容全部拷贝进去。用clone方法创建对象并不会调用任何构造函数。
// 要使用clone方法，我们需要先实现Cloneable接口并实现其定义的clone方法，使得执行clone方法的时候进行深度拷贝。
// 4、反序列化
//所谓的序列化：就是把对象通过流的方式存储到文件中. 注意：此对象要重写Serializable接口才能被序列化
//反序列化: 把字节内容读取进来，还原为java对象 ObjectInputStream用来读取字节内容，还原（反序列化）为java对象

class Student implements Cloneable, Serializable {
//    private static final long serialVersionUID = 1L;

    private int id;

    public Student() {
        System.out.println("no params");
    }

    public Student(Integer id) {
        this.id = id;
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void printParam() {
        System.out.println(id);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


public class TestCreateObj {

    @Test
    public void test1() {
        Student student = new Student();
        student.print("cn/jasonhu/learn");
    }

    @Test
    public void test21() throws Exception {
        // Student student = (Student) Class.forName("Student").newInstance();
        Student student = Student.class.newInstance();
        student.print("cn/jasonhu/learn");
    }

    @Test
    public void test22() throws Exception {
        Constructor<Student> constructor = Student.class.getConstructor();
        Student student = constructor.newInstance();
        student.print("cn/jasonhu/learn");

        Constructor<Student> constructor1 = Student.class
                .getDeclaredConstructor(new Class[]{Integer.class});
        Student student1 = constructor1.newInstance(new Object[]{1});
        student1.printParam();
    }

    @Test
    public void test3() throws Exception {
        Student s1 = new Student();
        Student s2 = (Student) s1.clone();
        Student s3 = s1;

        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
    }

    @Test
    public void test4() throws Exception {
        Student s1 = new Student();
        // Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
        out.writeObject(s1);
        out.close();

        //Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
        Student student = (Student) in.readObject();
        in.close();

        student.print("cn/jasonhu/learn");
        System.out.println(student.hashCode());

        System.out.println(UUID.randomUUID());

        Object a = null;
        Long timestamp = Long.valueOf(String.valueOf(""));
        System.out.println(timestamp);
    }

}
