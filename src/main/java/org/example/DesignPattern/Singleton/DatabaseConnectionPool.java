package org.example.DesignPattern.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * In applications that interact with a database, creating a new connection for every request is expensive. A Singleton can manage a pool of database connections, ensuring that connections are reused efficiently.
 */
public class DatabaseConnectionPool {
    private static DatabaseConnectionPool instance;
    private List<Connection> connectionPool;
    private static final int POOL_SIZE = 5;

    private DatabaseConnectionPool() {
        connectionPool = new ArrayList<>();
        initializePool();
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionPool.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionPool();
                }
            }
        }
        return instance;
    }

    private void initializePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new RuntimeException("No connections available in the pool");
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public static void main(String[] args) {
        DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

        Connection conn = pool.getConnection();
        System.out.println("Connection fetched from pool");

        // Use the connection...
        pool.releaseConnection(conn);
        System.out.println("Connection released back to pool");
    }
}