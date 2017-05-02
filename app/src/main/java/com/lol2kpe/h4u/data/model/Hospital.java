package com.lol2kpe.h4u.data.model;

import java.util.HashSet;
import java.util.Set;

import xyz.samhal.openinghours.OpeningHours;

public class Hospital extends Place implements java.io.Serializable {
    private String hospitalType = "";
    private Set<Employee> employees = new HashSet<>();
    public Hospital(Hospital hospital){
        super(hospital);
        this.hospitalType = hospital.hospitalType;
    }

    public Hospital() {
        super();
    }

    public Hospital setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
        return this;
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

    public Hospital setOpeningHours(OpeningHours openingHours) {
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