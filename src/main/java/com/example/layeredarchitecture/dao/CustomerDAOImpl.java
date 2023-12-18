package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeUpdate("SELECT * FROM Customer");

        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        while (rst.next()){
            dtoList.add(new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
              )
            );
        }
        return dtoList;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id, name, address) VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getAddress());
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?", id);
    }


    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.executeUpdate("SELECT * FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeUpdate("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.executeUpdate("SELECT * FROM Customer WHERE id=?",s);
        rst.next();
        return new CustomerDTO(rst.getString(1),
                rst.getString(2),
                rst.getString(3));
    }
}

