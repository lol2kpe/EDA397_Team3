package com.lol2kpe.h4u;

public class Hospital {

    String name;
    int id;
    String hospitalType;
    Double latitude;
    Double longitude;
    int rating;
    String openingHours;
    String address;
    String phoneNumber;
    public Hospital(String name, int id, String hospitalType, Double latitude, Double longitude, int rating,
                    String openingHours, String address, String phoneNumber){
        this.name = name;
        this.id = id;
        this.hospitalType = hospitalType;
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
    public int getId () {
        return id;
    }
    public String getHospitalType() {
        return hospitalType;
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
    public Hospital setName (String name) {
        this.name = name;
        return this;
    }
    public Hospital setId (int id) {
        this.id = id;
        return this;
    }
    public Hospital setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
        return this;
    }
    public Hospital setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    public Hospital setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
    public Hospital setRating(int rating) {
        this.rating = rating;
        return this;
    }
    public Hospital setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
        return this;
    }
    public Hospital setAddress(String address) {
        this.address = address;
        return this;
    }
    public Hospital setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    @Override
    public String toString() {
        return "" + name  + "\n"+ id + "\n" + hospitalType + "\n" + latitude + "\n" + longitude +
                "\n" + rating + "\n" + openingHours + "\n" + address + "\n" + phoneNumber;
    }
}