package com.example.layeredarchitecture.util;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        if (sql.startsWith("SELECT")) {
            return (T)pstm.executeQuery();
        } else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }
    }

}

