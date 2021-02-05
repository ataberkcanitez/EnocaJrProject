package com.ataberkcanitez.enocajrtest.service.impl;

import com.ataberkcanitez.enocajrtest.io.repository.CustomerRepository;
import com.ataberkcanitez.enocajrtest.exceptions.CustomerServiceException;
import com.ataberkcanitez.enocajrtest.io.entity.CustomerEntity;
import com.ataberkcanitez.enocajrtest.service.CustomerService;
import com.ataberkcanitez.enocajrtest.shared.Utils;
import com.ataberkcanitez.enocajrtest.shared.dto.CustomerDto;
import com.ataberkcanitez.enocajrtest.shared.dto.OrdersDto;
import com.ataberkcanitez.enocajrtest.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Utils utils;

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        for(int i = 0; i < customer.getOrders().size(); ++i){
            OrdersDto order = customer.getOrders().get(i);
            order.setCustomerDetails(customer);
            order.setOrderId(utils.generatePublicId(30));
            customer.getOrders().set(i, order);
        }

        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);

        customerEntity.setCustomerId(utils.generatePublicId(30));

        CustomerEntity storedCustomer = customerRepository.save(customerEntity);

        CustomerDto returnValue = modelMapper.map(storedCustomer, CustomerDto.class);


        return returnValue;
    }

    @Override
    public CustomerDto getCustomerByCustomerId(String id) {
        CustomerDto returnValue = new CustomerDto();
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(id);

        if(customerEntity == null) throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        returnValue = new ModelMapper().map(customerEntity, CustomerDto.class);


        return returnValue;
    }

    @Override
    public CustomerDto updateCustomer(String customerId, CustomerDto customer) {
        CustomerDto returnValue = new CustomerDto();
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(customerId);
        if(customerEntity == null) throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setAge(customer.getAge());

        CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
        returnValue = new ModelMapper().map(updatedCustomer, CustomerDto.class);

        return returnValue;
    }

    @Override
    public void deleteCustomer(String customerId) {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(customerId);
        if(customerEntity == null) throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        customerRepository.delete(customerEntity);
    }

    @Override
    public List<CustomerDto> getCustomers(int page, int limit) {
        List<CustomerDto> returnValue = new ArrayList<>();

        if (page > 0) page -= 1;

        Pageable pageable = PageRequest.of(page, limit);
        Page<CustomerEntity> customerPage = customerRepository.findAll(pageable);

        List<CustomerEntity> customers = customerPage.getContent();

        for(CustomerEntity customerEntity : customers){
            CustomerDto customerDto = new ModelMapper().map(customerEntity, CustomerDto.class);
            returnValue.add(customerDto);
        }

        return returnValue;
    }


}
