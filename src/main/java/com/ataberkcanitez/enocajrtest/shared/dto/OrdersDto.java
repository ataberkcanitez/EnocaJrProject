package com.ataberkcanitez.enocajrtest.shared.dto;

public class OrdersDto {
    private Long id;

    private String orderId;
    private String item;
    private Double price;
    private CustomerDto customerDetails;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CustomerDto getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDto customerDetails) {
        this.customerDetails = customerDetails;
    }
}
