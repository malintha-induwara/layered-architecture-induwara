package com.example.layeredarchitecture.util;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    private static Connection connection;

    static {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void autoCommitFalse() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public static void rollback() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

}

