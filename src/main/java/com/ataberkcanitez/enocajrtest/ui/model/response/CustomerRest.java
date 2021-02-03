package com.ataberkcanitez.enocajrtest.ui.model.response;

import java.util.List;

public class CustomerRest {

    private String customerId;
    private String firstName;
    private String lastName;
    private int age;
    private List<OrdersRest> orders;


    public void setOrders(List<OrdersRest> orders) {
        this.orders = orders;
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


    public List<OrdersRest> getOrders() {
        return orders;
    }
}
