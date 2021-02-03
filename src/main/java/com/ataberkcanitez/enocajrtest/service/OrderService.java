package com.ataberkcanitez.enocajrtest.service;

import com.ataberkcanitez.enocajrtest.shared.dto.OrdersDto;

import java.util.List;

public interface OrderService {
    List<OrdersDto> getOrders(String customerId);
    OrdersDto getOrder(String orderId);
}
