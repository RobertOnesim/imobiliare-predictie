package ro.uaic.info.data_mining.database_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dryflo on 4/24/2016.
 */
public class DatabaseConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://85.122.23.50/stefania.baincescu";
    private static final String USER = "stefania.baincescu";
    private static final String PASS = "Alex0974";

    private static DatabaseConnection instance = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (null == instance)
            instance = new DatabaseConnection();

        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
