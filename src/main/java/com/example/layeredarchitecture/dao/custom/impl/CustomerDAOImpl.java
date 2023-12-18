package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CustomerDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
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
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id, name, address) VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?", id);
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.executeUpdate("SELECT * FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
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
    public CustomerDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.executeUpdate("SELECT * FROM Customer WHERE id=?",s);
        rst.next();
        return new CustomerDTO(rst.getString(1),
                rst.getString(2),
                rst.getString(3));
    }
}

