package com.niit.stackroute.SpringMongoApp.service;

import com.niit.stackroute.SpringMongoApp.exception.CustomerAlreadyExistsException;
import com.niit.stackroute.SpringMongoApp.exception.CustomerNotFoundException;
import com.niit.stackroute.SpringMongoApp.model.Customer;

import java.util.List;
import com.niit.stackroute.SpringMongoApp.model.*;
import com.niit.stackroute.SpringMongoApp.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.niit.stackroute.SpringMongoApp.repository.CustomerRespository;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRespository customerRepository;

    @Autowired     //which design pattern ? DI (Dependency Injection) constructor injection
    public CustomerServiceImpl(CustomerRespository customerRepository) {
        this.customerRepository = customerRepository;

    }


    @Override
    public Customer saveCustomerDetail(Customer customer) throws CustomerAlreadyExistsException {

        if(customerRepository.findById(customer.getCustomerId()).isPresent())
        {
            throw new CustomerAlreadyExistsException();
        }
        return customerRepository.save(customer);


//        return null;
    }

    @Override
    public boolean deleteCustomer(int customerCode) throws CustomerNotFoundException {

        boolean flag = false;
        if(customerRepository.findById(customerCode).isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        else {
            customerRepository.deleteById(customerCode);
            flag = true;
        }
        return flag;

        //        return false;
    }

    @Override
    public List<Customer> getAllCustomerDetail() throws Exception {
        return customerRepository.findAll();
    }



    @Override
    public List<Customer> getAllCustomersByCity(String city) throws CustomerNotFoundException {
        if(customerRepository.findAllCustomerFromCity(city).isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        return customerRepository.findAllCustomerFromCity(city); //custom query methon defined in repo layer

    }

    //    return null;
   }

