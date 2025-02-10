package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static PostgresDB instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public PostgresDB(String host, String username, String password, String dbName) {
        this.url = host + "/" + dbName;
        this.username = username;
        this.password = password;
        this.connection = initConnection();
    }

    public static PostgresDB getInstance(String host, String username, String password, String dbName) {
        if (instance == null) {
            instance = new PostgresDB(host, username, password, dbName);
        }
        return instance;
    }

    private Connection initConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            System.out.println("Failed to connect to postgres: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Connection close error: " + ex.getMessage());
            }
        }
    }
}

