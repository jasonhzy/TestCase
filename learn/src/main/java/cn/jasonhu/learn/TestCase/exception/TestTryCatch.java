package cn.jasonhu.learn.TestCase.exception;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

public class TestTryCatch {

    /**
     * try-with-resource (java7引入)
     * 提供了更优雅的方式来实现资源的自动释放，自动释放的资源需要是实现了 AutoCloseable 接口的类
     */
    @Test
    public void test1() {
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try (Scanner scanner = new Scanner(new FileInputStream("/tmp/a"), "UTF-8")) {
            // code
            if (scanner.hasNext()) {
                String str1 = scanner.next();
                System.out.println("输入的数据为：" + str1);
            }
        } catch (IOException e) {
            // handle exception
        }
    }


    @Test
    public void test3(){
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
                GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(new File("out.txt")))) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 在上述代码中，我们从FileInputStream中读取字节，并且写入到GZIPOutputStream中。GZIPOutputStream实际上是FileOutputStream的装饰器。由于try-with-resource的特性，
         * 实际编译之后的代码会在后面带上finally代码块，并且在里面调用fin.close()方法和out.close()方法。我们再来看GZIPOutputStream类的close方法：
         */
        // public void close() throws IOException {
        // if (!closed) {
        // finish();
        // if (usesDefaultDeflater)
        // def.end();
        // out.close();
        // closed = true;
        // }
        // }
        /**
         * GZIPOutputStream类的close方法中out变量实际上代表的是被装饰的FileOutputStream类。在调用out变量的close方法之前，GZIPOutputStream还做了finish操作，该操作还会继续往FileOutputStream中写压缩信息，
         * 此时如果出现异常，则会out.close()方法被略过，然而这个才是最底层的资源关闭方法。正确的做法是应该在try-with-resource中单独声明最底层的资源，保证对应的close方法一定能够被调用。
         * 在刚才的例子中，我们需要单独声明每个FileInputStream以及FileOutputStream，例子即：
         */
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
             FileOutputStream fout = new FileOutputStream(new File("out.txt"));
             GZIPOutputStream out = new GZIPOutputStream(fout)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//实现了AutoCloseable 接口的类
class MyAutoClosable implements AutoCloseable {
    public void doIt() {
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }

    public static void main(String[] args) {
        try(MyAutoClosable myAutoClosable = new MyAutoClosable()){
            myAutoClosable.doIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
