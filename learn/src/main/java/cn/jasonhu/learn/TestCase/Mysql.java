package cn.jasonhu.learn.TestCase;

import java.sql.*;

public class Mysql {

    public static void main(String[] args) {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url = "jdbc:mysql://112.126.96.71:3090/gjg6?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=true&failOverReadOnly=false"; // JDBC的URL
        // 调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "joseph", "joseph");
            // 创建一个Statement对象
            Statement stmt = conn.createStatement(); // 创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = "select * from login"; // 要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
            while (rs.next()) {
                int id = rs.getInt("id");
                String playerName = rs.getString("playerName");
                System.out.println("ID: " + id + ", Name：" + playerName);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
