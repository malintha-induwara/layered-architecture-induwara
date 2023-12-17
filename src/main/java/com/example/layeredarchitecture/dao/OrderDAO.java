package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
    boolean getOrderDetail(String orderId) throws SQLException, ClassNotFoundException ;
    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
