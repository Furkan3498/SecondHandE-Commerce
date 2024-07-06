package com.furkanceylan.secondhand.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String country;
    private  String postCode;


     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id" , nullable = false)
     private Users user;


    public UserDetails(Long id, String phoneNumber, String address, String city, String country, String postCode, Users user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.user = user;
    }

    public UserDetails(String phoneNumber, String address, String city, String country, String postCode, Users user) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.user = user;
    }

    public UserDetails() {

    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public Users getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(postCode, that.postCode) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, address, city, country, postCode, user);
    }
}
