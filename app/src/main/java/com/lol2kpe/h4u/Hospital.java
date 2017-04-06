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
                    String openingHours, String address, String phoneNumber) {
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

    public String getName() {
        return name;
    }

    public Hospital setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Hospital setId(int id) {
        this.id = id;
        return this;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public Hospital setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Hospital setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Hospital setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Hospital setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public Hospital setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Hospital setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Hospital setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "" + name + "\n" + id + "\n" + hospitalType + "\n" + latitude + "\n" + longitude +
                "\n" + rating + "\n" + openingHours + "\n" + address + "\n" + phoneNumber;
    }
}