import java.sql.*;
public class Mysql {
    public static void main(String[] args){
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url="jdbc:mysql://54.238.133.224:4423/trade_api?useUnicode=true&characterEncoding=utf8&useSSL=false&autoReconnect=true&failOverReadOnly=false";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "bitup_api", "yEjbeusx4d3bMIn2#333");
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = "select * from trade_api_history order by created_at desc limit 1";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
            while (rs.next()) {
                int id  = rs.getInt("id");
                String api_key = rs.getString("api_key");
                System.out.println("ID: " + id + ", API KEY：" + api_key);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}