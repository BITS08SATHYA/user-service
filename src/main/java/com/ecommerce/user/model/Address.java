package com.ecommerce.user.model;


import lombok.*;


@Getter@Setter@ToString
public class Address {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}
