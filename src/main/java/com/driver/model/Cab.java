package com.driver.model;

import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity(name="cab")
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int perkmRate;
    boolean available;
    @OneToOne
    @JoinColumn
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Cab() {
    }

    public Cab(int id, int perkmRate, boolean available) {
        this.id = id;
        this.perkmRate = perkmRate;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerkmRate() {
        return perkmRate;
    }

    public void setPerkmRate(int perkmRate) {
        this.perkmRate = perkmRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable()
    {
        return available;
    }

}