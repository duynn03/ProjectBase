package com.example.nguyenduy.projectbase.base.architectureComponents.database.entity;

import android.arch.persistence.room.Ignore;


public class Address {

    private String street;

    private String state;

    private String city;

    private int postCode;

    public Address() {
    }

    @Ignore
    public Address(String street, String state, String city, int postCode) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (null != street) {
            builder.append(", street: " + street);
        }
        if (null != state) {
            builder.append(", state: " + state);
        }
        if (null != city) {
            builder.append(", fullName: " + city);
        }
        builder.append(", postCode: " + postCode + "");
        return builder.toString();

    }
}
