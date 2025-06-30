package cn.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
        //2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/itcast";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        String sql = "update account set money = 5000 where id < 10";

        Statement statement = conn.createStatement();

        int count = statement.executeUpdate(sql);

        System.out.println(count);

        //3. 释放资源
        statement.close();
        conn.close();
    }
}
