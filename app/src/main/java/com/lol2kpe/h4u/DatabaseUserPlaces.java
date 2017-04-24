package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-20.
 */

public class DatabaseUserPlaces {

    int _id;
    String _name;
    double _latitude;
    double _longitude;
    public DatabaseUserPlaces() {}

    public DatabaseUserPlaces(int id, String name, double latitude, double longitude) {
        this._id = id;
        this._name = name;
        this._latitude = latitude;
        this._longitude = longitude;
    }
    public DatabaseUserPlaces(String name, double latitude, double longitude) {
        this._name = name;
        this._latitude = latitude;
        this._longitude = longitude;
    }
    public int getId() {
        return this._id;
    }
    public void setId(int id) {
        this._id = id;
    }
    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }
    public double getLatitude() {
        return this._latitude;
    }
    public void setLatitude(double latitude) {
        this._latitude = latitude;
    }
    public double getLongitude() {
        return this._longitude;
    }
    public void setLongitude(double longitude) {
        this._longitude = longitude;
    }

}
