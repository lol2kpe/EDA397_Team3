package com.lol2kpe.h4u;

public class Pharmacy {

    String name;
    int id;
    String pharmacyType;
    Double latitude;
    Double longitude;
    int rating;
    String openingHours;
    String address;
    String phoneNumber;

    public Pharmacy(String name, int id, String pharmacyType, Double latitude, Double longitude, int rating,
                    String openingHours, String address, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.pharmacyType = pharmacyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.openingHours = openingHours;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Pharmacy setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getPharmacyType() {
        return pharmacyType;
    }

    public Pharmacy setPharmacyType(String pharmacyType) {
        this.pharmacyType = pharmacyType;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Pharmacy setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Pharmacy setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Pharmacy setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public Pharmacy setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Pharmacy setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Pharmacy setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "" + name + "\n" + id + "\n" + pharmacyType + "\n" + latitude + "\n" + longitude +
                "\n" + rating + "\n" + openingHours + "\n" + address + "\n" + phoneNumber;
    }
}