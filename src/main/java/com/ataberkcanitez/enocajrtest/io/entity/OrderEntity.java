package com.ataberkcanitez.enocajrtest.io.entity;

import com.ataberkcanitez.enocajrtest.shared.dto.CustomerDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 15126731L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30, nullable = false)
    private String orderId;

    @Column(nullable = false, length = 50)
    private String item;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "customers_id")
    private CustomerEntity customerDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public CustomerEntity getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerEntity customerDetails) {
        this.customerDetails = customerDetails;
    }
}
