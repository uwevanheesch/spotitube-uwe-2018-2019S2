package nl.han.oose.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/spotitube?useSSL=false";
    private static final String DB_USER = "uwe";
    private static final String DB_PASS = "uwepass";
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECTION_URL,
                    DB_USER,
                    DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
