package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.PlaceOrderDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.SQLException;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    @Override
    public boolean saveOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetails VALUES (?,?,?,?)", dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice());
    }



}

