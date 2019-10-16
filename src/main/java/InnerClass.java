//import EnclosingOne.InsideOne;

class EnclosingOne {

    public class InsideOne {

    }
}

public class InnerClass {

    public static void main(String[] args) {
        EnclosingOne eo = new EnclosingOne();
        //insert code here
//        InsideOne ei1 = eo.new InsideOne();
        EnclosingOne.InsideOne ei2 = eo.new InsideOne();
    }

}