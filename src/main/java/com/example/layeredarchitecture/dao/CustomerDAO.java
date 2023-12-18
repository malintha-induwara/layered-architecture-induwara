package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> getAllCustomers() throws Exception;

    boolean saveCustomer(CustomerDTO dto) throws Exception;

    boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    boolean deleteCustomer(String id) throws Exception;

    boolean existCustomer(String id) throws Exception;

    String getLastCustomerId() throws Exception;

    CustomerDTO searchCustomer(String s) throws Exception;


}
