package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;


public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeUpdate("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean getOrderDetail(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("SELECT * FROM OrderDetails WHERE oid=?", orderId);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (oid, date, customerId) VALUES (?,?,?)", dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId());
    }


}

