package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity(name="customer")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String password;
    private String mobile;
    //customer is parent wrt to tripBooking
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<TripBooking> customertripBookingList;

    public List<TripBooking> getCustomertripBookingList() {
        return customertripBookingList;
    }

    public void setCustomertripBookingList(List<TripBooking> customertripBookingList) {
        this.customertripBookingList = customertripBookingList;
    }

    public Customer(int customerId, String password, String mobile) {
        this.customerId = customerId;
        this.password = password;
        this.mobile = mobile;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}