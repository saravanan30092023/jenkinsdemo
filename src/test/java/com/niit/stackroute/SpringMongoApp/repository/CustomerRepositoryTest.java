package com.niit.stackroute.SpringMongoApp.repository;
import com.niit.stackroute.SpringMongoApp.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.niit.stackroute.SpringMongoApp.repository.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import com.niit.stackroute.SpringMongoApp.repository.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired      //property DI
    private CustomerRespository customerRepository;

    private Address address;
    private Customer customer;


    @BeforeEach
    void setUp() {
        address = new Address("Los Angeles", "California ", "pincode1");
        customer = new Customer(1001, "Jonny", address, 12345);
    }


    @AfterEach
    void tearDown() {
        address = null;
        customer = null;
        customerRepository.deleteAll();

    }


    @Test
    @DisplayName("Test case for saving customer object")
    void givenCustomerToSaveShouldReturnSavedCustomer() {
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1); //assertion  to check the expectation
        assertEquals(customer.getCustomerId(), customer1.getCustomerId());//assertion check
    }


    @Test
    @DisplayName("Test case for deleting customer object")
    public void givenCustomerToDeleteShouldDeleteCustomer() {
        customerRepository.insert(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        customerRepository.delete(customer1);
        assertEquals(Optional.empty(), customerRepository.findById(customer.getCustomerId()));

    }

    @Test
    @DisplayName("Test case for retrieving all the  customer object")
    public void givenCustomerReturnAllCustomerDetails() {

        customerRepository.insert(customer);
        Address address1 = new Address("Jacksonville", "Florida ", "pincode2");
        Customer customer1 = new Customer(1002, "Harry", address1, 123456);
        customerRepository.insert(customer1);

        List<Customer> list = customerRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Harry", list.get(1).getCustomerName());

    }



}
