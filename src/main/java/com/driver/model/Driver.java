package com.driver.model;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.List;

@Entity(name="driver")
public class Driver{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String password;
    private String mobile;
    //one to one with cab
    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private Cab cab;


    //It is parent wrt tripbooking
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> drivertripBookingList;

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getDrivertripBookingList() {
        return drivertripBookingList;
    }

    public void setDrivertripBookingList(List<TripBooking> drivertripBookingList) {
        this.drivertripBookingList = drivertripBookingList;
    }

    public Driver() {
    }

    public Driver(int customerId, String password, String mobile) {
        this.customerId = customerId;
        this.password = password;
        this.mobile = mobile;
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