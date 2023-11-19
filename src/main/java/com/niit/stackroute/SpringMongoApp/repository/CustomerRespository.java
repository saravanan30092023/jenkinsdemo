package com.niit.stackroute.SpringMongoApp.repository;
import com.niit.stackroute.SpringMongoApp.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRespository extends MongoRepository<Customer,Integer>{

    //SQL Equivalent : SELECT * FROM <tablename> WHERE <columnname>=?
    @Query("{'customerAddress.city' : {$in : [?0]}}") // mongod custom query
    List<Customer> findAllCustomerFromCity(String city); //custom method

}
