package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class HospitalHelper {

    String name;
    String hospitalType;
    String latitude;
    String longitude;
    int rating;
    String comment;
    String openingHours;
    String address;
    String phoneNumber;
    public HospitalHelper(String name, String hospitalType, String latitude, String longitude, int rating, String comment,
                          String openingHours, String address, String phoneNumber){
        this.name = name;
        this.hospitalType = hospitalType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.comment = comment;
        this.openingHours = openingHours;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public String getName () {
        return name;
    }
    public String getHospitalType() {
        return hospitalType;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public int getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
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
    public void setName (String name) {
        this.name = name;
    }
    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "" + name  + "\n" + hospitalType + "\n" + latitude + "\n" + longitude +
                "\n" + rating + "\n" + comment + "\n" + openingHours + "\n" + address + "\n" + phoneNumber;
    }
}
