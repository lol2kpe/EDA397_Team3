package com.lol2kpe.h4u.data.model;

import xyz.samhal.openinghours.OpeningHours;

public class Pharmacy extends Place {

    public Pharmacy(){
        super();
    }

    public Pharmacy(Pharmacy pharmacy){
        super(pharmacy);
    }

    public Pharmacy setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Pharmacy setAddress(String address) {
        this.address = address;
        return this;
    }

    public Pharmacy setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public Pharmacy setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Pharmacy setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Pharmacy setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Pharmacy setName(String name) {
        this.name = name;
        return this;
    }

    public Pharmacy setId(int id) {
        this.id = id;
        return this;
    }
}