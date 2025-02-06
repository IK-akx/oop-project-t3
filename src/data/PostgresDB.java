package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static PostgresDB instance;
    private Connection connection;
    private String host;
    private String username;
    private String password;
    private String dbName;

    public PostgresDB(String host, String username, String password, String dbName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.connection = getConnection();
    }

    public static PostgresDB getInstance(String host, String username, String password, String dbName) {
        if (instance == null) {
            instance = new PostgresDB(host, username, password, dbName);
        } else if (instance.getConnection() == null) {
            instance = new PostgresDB(host, username, password, dbName);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        String connectionUrl = host + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            System.out.println("Failed to connect to postgres: " + e.getMessage());
        }
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
