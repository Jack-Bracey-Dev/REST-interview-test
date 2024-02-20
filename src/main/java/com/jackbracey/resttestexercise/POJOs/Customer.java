package com.jackbracey.resttestexercise.POJOs;

import com.jackbracey.resttestexercise.Entities.CustomerEntity;

import java.util.List;

public class Customer {

    private Integer id;

    private String name;

    private String addressLineOne;

    private String addressLineTwo;

    private String town;

    private String county;

    private String country;

    private String postcode;

    public Customer(Integer id,
                    String name,
                    String addressLineOne,
                    String addressLineTwo,
                    String town,
                    String county,
                    String country,
                    String postcode) {
        this.id = id;
        this.name = name;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public static List<CustomerEntity> convertAllToEntity(List<Customer> customers) {
        return customers
                .stream()
                .map(Customer::convertToEntity)
                .toList();
    }

    public static CustomerEntity convertToEntity(Customer customer) {
        return new CustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getAddressLineOne(),
                customer.getAddressLineTwo(),
                customer.getTown(),
                customer.getCounty(),
                customer.getCountry(),
                customer.getPostcode());
    }

}
