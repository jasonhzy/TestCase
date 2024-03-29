package cn.jasonhu.learn.TestCase;

public class Test {

    public static void main(String[] args) {
        System.out.println(new B().getValue());
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
}
