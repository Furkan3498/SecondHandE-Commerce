package com.furkanceylan.secondhand.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_name")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(unique = true)
    private String mail;
    private String firstName;
    private String lastName;
    private String middleName;
    private Boolean isActive;

    public User(Long id, String mail, String firstName, String lastName, String middleName, Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isActive = isActive;
    }
    public User(String mail, String firstName, String lastName, String middleName) {

        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;

    }

    public User(Long id, String mail, String firstName, String lastName, String middleName) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public User(String mail, String firstName, String lastName, String middleName, Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isActive = isActive;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Boolean getActive(){
        return isActive;
    }

    /*Modelde hiç setter yok. Bunun sebebi immutability olması.*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return mail.equals(that.mail) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && middleName.equals(that.middleName) && isActive.equals(that.isActive);
    }

    /*Oluşturulan tüm UserInformation nesneleri id içermediğinden equals'a id'yi dahil etmedik. Diğer türlü id içermeyen
     * UserInformationlar için hata alırdık.*/

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, firstName, lastName, middleName, isActive);
    }

    /*Bütün value'ları aynı olan 2 UserInformation objesi aslında eşit olmuyor Object'den gelen
      equals ve hashcode'u ezmezsek eğer! -> !!!ONEMLI BILGI!!!
      Çünkü Object'den gelen equals ve hashcode farklı obje hashcodeları oluşturuyor.
      UserServiceTest'de testCreateUser_itShouldReturnCreatedUserDto metodunda mesela 2 tane UserInformation objesi
       oluşturduk. 2 ayrı obje biri id alıyor diğeri almıyor. Ama equals ve hashcode ezmediğimiz için daha önce
      */
}