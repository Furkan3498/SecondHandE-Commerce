package com.furkanceylan.secondhand.dto;


import java.util.List;

public class UserDto {

    private String mail;
    private String firstName;
    private String lastName;
    private String middleName;
    private List<UserDetailsDto> userDetailsDtos;

    public List<UserDetailsDto> getUserDetailsDtos() {
        return userDetailsDtos;
    }

    public void setUserDetailsDtos(List<UserDetailsDto> userDetailsDtos) {
        this.userDetailsDtos = userDetailsDtos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
