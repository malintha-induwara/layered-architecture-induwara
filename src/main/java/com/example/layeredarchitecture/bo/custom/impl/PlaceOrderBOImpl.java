package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.PlaceOrderDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.PlaceOrderDAOImpl;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrderDAO orderDAO=new OrderDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    PlaceOrderDAO orderDetailDAO = new PlaceOrderDAOImpl();


    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }


    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
            boolean isOrderExists = orderDAO.exist(orderId);

            /*if order id already exists*/
            if (isOrderExists) {
                return false;
            }

            TransactionUtil.autoCommitFalse();

            boolean isOrderSaved = orderDAO.save(new OrderDTO(orderId, orderDate, customerId, null, null));

            if (!isOrderSaved) {
                TransactionUtil.rollback();
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                boolean isOrderDetailSaved = orderDetailDAO.saveOrderDetail(detail);

                if (!isOrderDetailSaved) {
                    TransactionUtil.rollback();
                    return false;
                }

                // Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());




// Subtract the quantity in the order details from the existing quantity on hand
                int updatedQtyOnHand = item.getQtyOnHand() - detail.getQty();
                item.setQtyOnHand(updatedQtyOnHand);


                boolean isUpdated = itemDAO.update(item);

                if (!isUpdated) {
                    TransactionUtil.rollback();
                    return false;
                }
            }

            TransactionUtil.commit();
            return true;
    }



    public ItemDTO findItem(String code) {
        try {
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

