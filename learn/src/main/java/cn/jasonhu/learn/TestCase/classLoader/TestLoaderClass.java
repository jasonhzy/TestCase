package cn.jasonhu.learn.TestCase.classLoader;

/**
 * @author jason hu
 * @since 2021/10/29 14:08
 */
public class TestLoaderClass {

    public void test() {

    }


    public static void main(String[] args) {
        try {
            //查看当前系统类路径中包含的路径
            System.out.println(System.getProperty("java.class.path"));
            //通过反射加载TwoParentTest类
            Class<?> forName = Class.forName(
                    "cn.jasonhu.learn.TestCase.classLoader.TestLoaderClass");
            /**
             * 查看实例是被哪个类加载的
             * 输出结果：sun.misc.Launcher$AppClassLoader@18b4aac2， 可确定是应用类加载器
             */

            System.out.println(forName.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
