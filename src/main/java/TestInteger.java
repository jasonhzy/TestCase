public class TestInteger {

    public static void change(Integer jj) {
        jj = 30;//将传递过来的引用，修改了
    }

    public static void test1() {
        Integer jk = 25;//jk是引用
        change(jk);//引用传递
        System.out.println(jk);//返回，25
    }

    public static void test2(){
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150, f5 = -128, f6 = -128, f7 = 127, f8 = 127;

        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
        System.out.println(f5 == f6);
        System.out.println(f7 == f8);
    }

    public static void test3(){
        Integer a = new Integer(3);
        Integer b = 3;                  // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b);     // false 两个引用没有引用同一对象
        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
    }


    public static void main(String[] args) {
        test3();
    }
}

//class Demo{
//    int a;
//    public Demo(int a){
//        this.a=a;
//    }
//}
//class TestQuote{
//    public static void main(String args[]){
//        Demo d1=new Demo(1);
//        Demo d2=new Demo(2);
//        System.out.println(d1.a);
//        System.out.println(d2.a);
//        function(d1,d2);
//        System.out.println(d1.a);
//        System.out.println(d2.a);
//    }
//    private static void function(Demo d1,Demo d2){
//        Demo temp;
//        temp=d1;
//        d1=d2;
//        d2=temp;
//    }
//}
