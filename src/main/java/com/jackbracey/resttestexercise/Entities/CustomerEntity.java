package com.jackbracey.resttestexercise.Entities;

import com.jackbracey.resttestexercise.POJOs.Customer;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @Column(name = "address_line_1")
    private String addressLineOne;

    @Column(name = "address_line_2")
    private String addressLineTwo;

    private String town;

    private String county;

    private String country;

    private String postcode;

    public CustomerEntity(Integer id,
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

    public CustomerEntity() {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerEntity entity))
            return false;

        return entity.getId().equals(this.id)
                && entity.getName().equals(this.name)
                && entity.getAddressLineOne().equals(this.addressLineOne)
                && entity.getAddressLineTwo().equals(this.addressLineTwo)
                && entity.getTown().equals(this.town)
                && entity.getCounty().equals(this.county)
                && entity.getCountry().equals(this.country)
                && entity.getPostcode().equals(this.postcode);
    }

    public static List<Customer> convertAllToPojo(List<CustomerEntity> entities) {
        return entities
                .stream()
                .map(CustomerEntity::convertToPojo)
                .toList();
    }

    public static Customer convertToPojo(CustomerEntity entity) {
        return new Customer(entity.getId(),
                entity.getName(),
                entity.getAddressLineOne(),
                entity.getAddressLineTwo(),
                entity.getTown(),
                entity.getCounty(),
                entity.getCountry(),
                entity.getPostcode());
    }

}
