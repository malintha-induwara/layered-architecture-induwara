package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.QueryDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public void orderDetails(OrderDTO dto) {

        try {
            ResultSet rst = SQLUtil.executeUpdate("SELECT\n" +
                    "    Orders.oid,\n" +
                    "    Orders.date,\n" +
                    "    Customer.id AS customerID,\n" +
                    "    Customer.name AS customerName,\n" +
                    "    Customer.address AS customerAddress,\n" +
                    "    OrderDetails.itemCode,\n" +
                    "    Item.description AS itemDescription,\n" +
                    "    OrderDetails.qty,\n" +
                    "    OrderDetails.unitPrice AS orderDetailUnitPrice,\n" +
                    "    Item.unitPrice AS itemUnitPrice\n" +
                    "FROM\n" +
                    "    Orders\n" +
                    "        JOIN\n" +
                    "    Customer ON Orders.customerID = Customer.id\n" +
                    "        JOIN\n" +
                    "    OrderDetails ON Orders.oid = OrderDetails.oid\n" +
                    "        JOIN\n" +
                    "    Item ON OrderDetails.itemCode = Item.code");


            while (rst.next()){
                System.out.println(rst.getString("oid"));
                System.out.println(rst.getString("date"));
                System.out.println(rst.getString("customerID"));
                System.out.println(rst.getString("customerName"));
                System.out.println(rst.getString("customerAddress"));
                System.out.println(rst.getString("itemCode"));
                System.out.println(rst.getString("itemDescription"));
                System.out.println(rst.getString("qty"));
                System.out.println(rst.getString("orderDetailUnitPrice"));
                System.out.println(rst.getString("itemUnitPrice"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
