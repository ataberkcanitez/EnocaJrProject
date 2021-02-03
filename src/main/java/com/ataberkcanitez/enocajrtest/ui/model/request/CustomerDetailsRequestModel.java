package com.ataberkcanitez.enocajrtest.ui.model.request;

import java.util.List;

public class CustomerDetailsRequestModel {

    private String firstName;
    private String lastName;
    private int age;
    private List<OrderRequestModel> orders;


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

    public List<OrderRequestModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRequestModel> orders) {
        this.orders = orders;
    }
}
