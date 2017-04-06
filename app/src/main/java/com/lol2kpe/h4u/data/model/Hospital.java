package com.lol2kpe.h4u.data.model;

public class Hospital extends Place {
    private String hospitalType;

    public Hospital(Hospital hospital){
        super(hospital);
        this.hospitalType = hospital.hospitalType;
    }

    public Hospital() {
        super();
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public Hospital setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
        return this;
    }

    public Hospital setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Hospital setAddress(String address) {
        this.address = address;
        return this;
    }

    public Hospital setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public Hospital setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Hospital setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Hospital setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Hospital setName(String name) {
        this.name = name;
        return this;
    }

    public Hospital setId(int id) {
        this.id = id;
        return this;
    }
}