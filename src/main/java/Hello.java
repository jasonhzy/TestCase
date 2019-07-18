import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Hello {

    public static void main(String[] args) {

        String s1 = "ok";
        String s2 = new String("ok");

        System.out.println(s1.hashCode() + "==" + s2.hashCode());

        Map<String, String> map = new HashMap<>();

        Integer i = 10;

        Double x = 1d;

        Boolean b = false;

        Float y = 1F;

        System.out.println(new Date());
    }
}
