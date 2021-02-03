package com.ataberkcanitez.enocajrtest.service.impl;

import com.ataberkcanitez.enocajrtest.io.repository.CustomerRepository;
import com.ataberkcanitez.enocajrtest.io.entity.CustomerEntity;
import com.ataberkcanitez.enocajrtest.io.entity.OrderEntity;
import com.ataberkcanitez.enocajrtest.io.repository.OrderRepository;
import com.ataberkcanitez.enocajrtest.service.OrderService;
import com.ataberkcanitez.enocajrtest.shared.dto.OrdersDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<OrdersDto> getOrders(String customerId) {

        List<OrdersDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(customerId);
        if (customerEntity == null) return returnValue;

        Iterable<OrderEntity> orders = orderRepository.findAllByCustomerDetails(customerEntity);

        for (OrderEntity orderEntity : orders){
            returnValue.add(modelMapper.map(orderEntity, OrdersDto.class));
        }

        return returnValue;
    }

    @Override
    public OrdersDto getOrder(String orderId) {
        OrdersDto returnValue = null;

        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        if (orderEntity != null){
            returnValue = new ModelMapper().map(orderEntity, OrdersDto.class);
        }

        return returnValue;
    }
}
