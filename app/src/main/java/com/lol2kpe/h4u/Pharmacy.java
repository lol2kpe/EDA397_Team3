package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

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
                    String openingHours, String address, String phoneNumber){
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
    public String getName () {
        return name;
    }
    public int getId(){return id;}
    public String getPharmacyType() {
        return pharmacyType;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public int getRating() {
        return rating;
    }
    public String getOpeningHours() {
        return openingHours;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Pharmacy setName (String name) {
        this.name = name;
        return this;
    }
    public Pharmacy setPharmacyType(String pharmacyType) {
        this.pharmacyType = pharmacyType;
        return this;
    }
    public Pharmacy setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    public Pharmacy setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
    public Pharmacy setRating(int rating) {
        this.rating = rating;
        return this;
    }
    public Pharmacy setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
        return this;
    }
    public Pharmacy setAddress(String address) {
        this.address = address;
        return this;
    }
    public Pharmacy setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    @Override
    public String toString() {
        return "" + name  + "\n"+ id + "\n" + pharmacyType + "\n" + latitude + "\n" + longitude +
                "\n" + rating + "\n" + openingHours + "\n" + address + "\n" + phoneNumber;
    }
}
