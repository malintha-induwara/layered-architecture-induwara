package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderDTO;

public interface QueryDAO extends SuperDAO {

    void orderDetails(OrderDTO dto);

}
