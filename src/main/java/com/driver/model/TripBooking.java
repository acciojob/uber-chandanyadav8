package com.driver.model;

import javax.persistence.*;

@Entity(name="tripBooking")
public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String fromLocation;
    private String toLocation;
    private int distinKm;
    @Enumerated(value = EnumType.STRING)
    TripStatus tripStatus;
    private int bill;

    //it is child wrt to Driver
    @ManyToOne
    @JoinColumn
    private Driver driver;

    //it is child wrt to customer
    @ManyToOne
    @JoinColumn
    private Customer customer;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TripBooking() {
    }

    public TripBooking(int customerId, String fromLocation, String toLocation, int distinKm, TripStatus tripStatus, int bill) {
        this.customerId = customerId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distinKm = distinKm;
        this.tripStatus = tripStatus;
        this.bill = bill;
    }

    public int getDistinKm() {
        return distinKm;
    }

    public void setDistinKm(int distinKm) {
        this.distinKm = distinKm;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
}