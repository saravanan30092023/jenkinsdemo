package com.niit.stackroute.SpringMongoApp.service;

import com.niit.stackroute.SpringMongoApp.model.*;
import com.niit.stackroute.SpringMongoApp.exception.*;

import java.util.List;

public interface CustomerService {

    Customer saveCustomerDetail(Customer customer) throws CustomerAlreadyExistsException;
    boolean deleteCustomer(int id) throws CustomerNotFoundException;
    List<Customer> getAllCustomerDetail() throws Exception;
    List<Customer> getAllCustomersByCity(String city) throws CustomerNotFoundException;
}
