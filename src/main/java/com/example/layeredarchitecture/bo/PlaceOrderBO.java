package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO {
    boolean existCustomer(String s) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException;

    boolean existItem(String s) throws SQLException, ClassNotFoundException;

    String generateOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetail(OrderDetailDTO detail) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code)
}
