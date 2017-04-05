package com.lol2kpe.h4u;

import android.location.Location;

/**
 * Created by sam on 2017-04-04.
 */

public class UserLocation extends Location {
    private String name = "";
    public UserLocation(Location l) {
        super(l);
    }
    public UserLocation setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }
}