package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> loadAllItems() throws Exception;
    public boolean deleteItem(String code) throws Exception;
    public boolean saveItem(ItemDTO dto) throws Exception;
    public boolean updateItems(ItemDTO dto) throws Exception;
    public boolean existItem(String code) throws Exception;
    String getLastItemId() throws Exception;
    ItemDTO searchItem(String s) throws Exception;

}
