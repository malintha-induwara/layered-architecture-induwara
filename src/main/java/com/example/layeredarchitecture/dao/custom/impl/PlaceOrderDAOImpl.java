package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.PaceOrderDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderDAOImpl implements PaceOrderDAO {

    @Override
    public boolean saveOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetails VALUES (?,?,?,?)", dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice());
    }



}

