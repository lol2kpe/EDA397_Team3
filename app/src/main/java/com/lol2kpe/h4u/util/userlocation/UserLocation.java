package com.lol2kpe.h4u.util.userlocation;

import android.location.Location;

/**
 * Created by sam on 2017-04-04.
 */

public class UserLocation extends Location {
    private String name = "";

    public UserLocation(Location l) {
        super(l);
    }

    public String getName() {
        return this.name;
    }

    public UserLocation setName(String name) {
        this.name = name;
        return this;
    }
}