package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {


        CustomerDAO customerDAO = new CustomerDAOImpl();

        @Override
        public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

            ArrayList<Customer> customers=customerDAO.getAll();
            ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
            for (Customer customer:customers) {
                customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
            }
            return customerDTOS;
        }

        @Override
        public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
            return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
        }

        @Override
        public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
            return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
        }


        @Override
        public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
            return customerDAO.delete(id);
        }

        @Override
        public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
            return customerDAO.generateId();
        }
        @Override
        public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
            Customer customer=customerDAO.search(id);
            CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
            return customerDTO;
        }

}

