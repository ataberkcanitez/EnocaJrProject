package com.ataberkcanitez.enocajrtest.shared.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 4123581231L;

    private long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private int age;
    private List<OrdersDto> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<OrdersDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersDto> orders) {
        this.orders = orders;
    }
}
