package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    static String url = "jdbc:mysql://localhost:3307/servletcrud";
    static String user= "root";
    static String password = "";
    static String driver ="com.mysql.cj.jdbc.Driver";
    static Connection connection =null;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            System.out.println("connected");
            connection = DriverManager.getConnection(url,user,password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
