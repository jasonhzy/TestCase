package exception;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestFinally {

    public  Map<String, String> getMap(){
        Map<String, String> map = new HashMap<>();
        map.put("KEY", "INIT");

        try{
            map.put("KEY", "TRY");
            return map;
        }catch (Exception e){
            map.put("KEY", "CATCH");
        }finally {
            map.put("KEY", "FINALLY");
            map = null;
        }
        return map;
    }

    @Test
    public void getResult(){
        System.out.println(getMap());
    }

}
