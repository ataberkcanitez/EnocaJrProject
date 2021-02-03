package com.ataberkcanitez.enocajrtest.service.impl;

import com.ataberkcanitez.enocajrtest.CustomerRepository;
import com.ataberkcanitez.enocajrtest.exceptions.CustomerServiceException;
import com.ataberkcanitez.enocajrtest.io.entity.CustomerEntity;
import com.ataberkcanitez.enocajrtest.service.CustomerService;
import com.ataberkcanitez.enocajrtest.shared.Utils;
import com.ataberkcanitez.enocajrtest.shared.dto.CustomerDto;
import com.ataberkcanitez.enocajrtest.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        CustomerEntity customerEntity = new CustomerEntity();

        BeanUtils.copyProperties(customer, customerEntity);

        customerEntity.setCustomerId(utils.generatePublicId(30));

        CustomerEntity storedCustomer = customerRepository.save(customerEntity);

        CustomerDto returnValue = new CustomerDto();
        BeanUtils.copyProperties(storedCustomer, returnValue);


        return returnValue;
    }

    @Override
    public CustomerDto getCustomerByCustomerId(String id) {
        CustomerDto returnValue = new CustomerDto();
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(id);

        if(customerEntity == null) throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(customerEntity, returnValue);


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
        BeanUtils.copyProperties(updatedCustomer, returnValue);



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
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customerEntity, customerDto);
            returnValue.add(customerDto);
        }

        return returnValue;
    }


}
