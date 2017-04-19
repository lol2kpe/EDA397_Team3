package com.lol2kpe.h4u.data.model;

import xyz.samhal.openinghours.OpeningHours;

/**
 * Created by sam on 4/6/17.
 */

public abstract class Place implements java.io.Serializable {
    protected int rating = 0;
    protected String address = "";
    protected transient OpeningHours openingHours = new OpeningHours();
    protected double latitude = 0;
    protected double longitude = 0;
    protected String phoneNumber = "";
    protected String name = "";
    protected int id = 0;

    public Place(){}

    public Place(Place place) {
        this.rating = place.rating;
        this.address = place.address;
        this.openingHours = place.openingHours;
        this.latitude = place.latitude;
        this.longitude = place.longitude;
        this.phoneNumber = place.phoneNumber;
        this.name = place.name;
        this.id = place.id;
    }


    public String getAddress() {
        return address;
    }

    public OpeningHours getOpeningHours() {
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


    public int getId() {
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
