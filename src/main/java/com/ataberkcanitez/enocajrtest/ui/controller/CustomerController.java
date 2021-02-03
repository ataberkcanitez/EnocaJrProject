package com.ataberkcanitez.enocajrtest.ui.controller;

import com.ataberkcanitez.enocajrtest.exceptions.CustomerServiceException;
import com.ataberkcanitez.enocajrtest.service.CustomerService;
import com.ataberkcanitez.enocajrtest.shared.dto.CustomerDto;
import com.ataberkcanitez.enocajrtest.ui.model.request.CustomerDetailsRequestModel;
import com.ataberkcanitez.enocajrtest.ui.model.response.CustomerRest;
import com.ataberkcanitez.enocajrtest.ui.model.response.ErrorMessages;
import com.ataberkcanitez.enocajrtest.ui.model.response.OperationStatusModel;
import com.ataberkcanitez.enocajrtest.ui.model.response.RequestOperationStatus;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public CustomerRest getCustomer(@PathVariable String id){
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = customerService.getCustomerByCustomerId(id);
        BeanUtils.copyProperties(customerDto, returnValue);

        return returnValue;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public CustomerRest createCustomer(@RequestBody CustomerDetailsRequestModel customerDetails) throws Exception {

        CustomerRest returnValue = new CustomerRest();

        if (customerDetails.getFirstName() == null || customerDetails.getFirstName().isEmpty())
        throw new CustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customerDetails, customerDto);

        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        BeanUtils.copyProperties(createdCustomer, returnValue);

        return returnValue;
    }

    @PutMapping(
            path = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public CustomerRest updatesCustomer(@PathVariable String id,@RequestBody CustomerDetailsRequestModel customerDetails){
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customerDetails, customerDto);

        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        BeanUtils.copyProperties(updatedCustomer, returnValue);

        return returnValue;
    }



    @DeleteMapping(
            path = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public OperationStatusModel deleteCustomer(@PathVariable String id){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        customerService.deleteCustomer(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<CustomerRest> getCustomers(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<CustomerRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers = customerService.getCustomers(page, limit);
        for(CustomerDto customerDto : customers){
            CustomerRest customerModel = new CustomerRest();
            BeanUtils.copyProperties(customerDto,customerModel);
            returnValue.add(customerModel);
        }


        return returnValue;
    }


}
