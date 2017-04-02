package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class DoctorHelper {
    String name;
    String doctorType;
    int rating;
    String hospitalAddress;
    String phoneNumber;
    String hospitalName;

    public DoctorHelper(String name, String doctorType, int rating, String hospitalAddress, String phoneNumber, String hospitalName) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    @Override
    public String toString() {
        return "" + name + "\n" + doctorType + "\n" + rating + "\n" + hospitalAddress + "\n"
                + phoneNumber + "\n" + hospitalName;
    }

}

