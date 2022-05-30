package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    //private static final String DRIVER = "mysql-connector-java";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "rubina";
    static Connection connection;

    public static Connection getConnection() {
        try {
            //Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Connection is success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
