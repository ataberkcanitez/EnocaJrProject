package com.ataberkcanitez.enocajrtest.service;

import com.ataberkcanitez.enocajrtest.shared.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customer);
    CustomerDto getCustomerByCustomerId(String id);
    CustomerDto updateCustomer(String customerId, CustomerDto customer);
    void deleteCustomer(String customerId);
    List<CustomerDto> getCustomers(int page, int limit);
}
