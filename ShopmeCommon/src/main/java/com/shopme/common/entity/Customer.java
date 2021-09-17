package com.shopme.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;
    
    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 64)
    private String addressLine1;

    @Column(length = 64)
    private String addressLine2;

    @Column(nullable = false, length = 45)
    private String city;

    @Column(nullable = false, length = 45)
    private String state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false)
    private Date createdTime;

    @Column(nullable = false)
    private boolean enabled;

    @Column(length = 64)
    private String verificationCode;

    public Customer() {
    }

    public Customer(Integer id, String email, String password, String firstName, String lastName, String phoneNumber,
            String addressLine1, String city, String state, Country country, String postalCode, Date createdTime,
            Boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTime = createdTime;
        this.enabled = enabled;
    }

    public Customer(String email, String password, String firstName, String lastName, String phoneNumber,
            String addressLine1, String city, String state, Country country, String postalCode, Date createdTime,
            Boolean enabled) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTime = createdTime;
        this.enabled = enabled;
    }

    public Customer(String email, String password, String firstName, String lastName, String phoneNumber,
            String addressLine1, String city, String state, Country country, String postalCode, Date createdTime,
            Boolean enabled, String verificationCode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTime = createdTime;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
    }

    public Customer(Integer id, String email, String password, String firstName, String lastName, String phoneNumber,
            String addressLine1, String addressLine2, String city, String state, Country country, String postalCode,
            Date createdTime, Boolean enabled, String verificationCode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTime = createdTime;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
    }

    public Customer(String email, String password, String firstName, String lastName, String phoneNumber,
            String addressLine1, String addressLine2, String city, String state, Country country, String postalCode,
            Date createdTime, Boolean enabled, String verificationCode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTime = createdTime;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "Customer [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public boolean isEnabled(){
        return enabled;
    }
}
