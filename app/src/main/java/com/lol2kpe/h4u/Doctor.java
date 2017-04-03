package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class Doctor {
    String name;
    String doctorType;
    int rating;
    String hospitalAddress;
    String phoneNumber;
    String hospitalName;

    public Doctor(String name, String doctorType, int rating, String hospitalAddress, String phoneNumber, String hospitalName) {
        this.name = name;
        this.doctorType = doctorType;
        this.rating = rating;
        this.hospitalAddress = hospitalAddress;
        this.phoneNumber = phoneNumber;
        this.hospitalName = hospitalName;
    }

    public String getName() {
        return name;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public int getRating() {
        return rating;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public Doctor setName(String name) {
        this.name = name;
        return this;
    }
    public Doctor setDoctorType(String doctorType) {
        this.doctorType = doctorType;
        return this;
    }
    public Doctor setRating(int rating) {
        this.rating = rating;
        return this;
    }
    public Doctor setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
        return this;
    }
    public Doctor setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public Doctor setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
        return this;
    }
    @Override
    public String toString() {
        return "" + name + "\n" + doctorType + "\n" + rating + "\n" + hospitalAddress + "\n"
                + phoneNumber + "\n" + hospitalName;
    }

}

