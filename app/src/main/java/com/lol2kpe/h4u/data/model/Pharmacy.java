package com.lol2kpe.h4u.data.model;

public class Pharmacy extends Place {
    public Pharmacy setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Pharmacy setAddress(String address) {
        this.address = address;
        return this;
    }

    public Pharmacy setOpeningHours(String openingHours) {
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

    public Pharmacy setId(String id) {
        this.id = id;
        return this;
    }
}