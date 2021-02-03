package com.ataberkcanitez.enocajrtest.io.repository;

import com.ataberkcanitez.enocajrtest.io.entity.CustomerEntity;
import com.ataberkcanitez.enocajrtest.io.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByCustomerDetails(CustomerEntity customerEntity);
    OrderEntity findByOrderId(String orderId);
}
