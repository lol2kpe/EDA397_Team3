package com.lol2kpe.h4u.data.model;

/**
 * Created by sam on 4/6/17.
 */

public abstract class Place {
    protected int rating = 0;
    protected String address = "";
    protected String openingHours = "";
    protected double latitude = 0;
    protected double longitude = 0;
    protected String phoneNumber = "";
    protected String name = "";
    protected String id = "";

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }


    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return this.name + "\n"
                + this.id + "\n"
                + this.latitude + "\n"
                + this.longitude + "\n"
                + this.rating + "\n"
                + this.openingHours + "\n"
                + this.address + "\n"
                + this.phoneNumber + "\n";
    }
}
