package com.ataberkcanitez.enocajrtest.io.repository;

import com.ataberkcanitez.enocajrtest.io.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long > {

    CustomerEntity findCustomerEntityByCustomerId(String id);





}
