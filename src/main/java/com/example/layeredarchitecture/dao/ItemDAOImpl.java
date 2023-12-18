package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.util.SQLUtil;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{


    @Override
    public ArrayList<ItemDTO> loadAllItems() throws Exception {

        ResultSet rst = SQLUtil.executeUpdate("SELECT * FROM Item");

        ArrayList<ItemDTO> dtoList = new ArrayList<>();
        while (rst.next()) {
            dtoList.add(new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean deleteItem(String code) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
    }

    @Override
    public boolean updateItems(ItemDTO dto) throws Exception {
        return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getCode());
    }

    @Override
    public boolean existItem(String code) throws Exception {
        ResultSet rst=SQLUtil.executeUpdate("SELECT code FROM Item WHERE code=?", code);
        return rst.next();
    }

    @Override
    public String getLastItemId() throws Exception {
        ResultSet rst = SQLUtil.executeUpdate("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO searchItem(String s) throws Exception {
        ResultSet rst = SQLUtil.executeUpdate("SELECT * FROM Item WHERE code=?", s);
        rst.next();
        return new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
    }
}

