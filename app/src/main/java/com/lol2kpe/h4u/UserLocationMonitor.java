package com.lol2kpe.h4u;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by sam on 2017-04-03.
 */

public class UserLocationMonitor{
    public static final String NETWORK_PROVIDER = LocationManager.NETWORK_PROVIDER;
    public static final String GPS_PROVIDER = LocationManager.GPS_PROVIDER;
    //Get the location as frequently as possible
    private static final long MIN_TIME = 0;
    private static final float MIN_DISTANCE = 0;
    private Location location;
    private LocationManager locationManager;
    private UserLocationListener userLocationListener = new UserLocationListener();

    public static UserLocationMonitor getMonitor(Context context){
        LocationManager locationManager = (LocationManager) context
                .getSystemService(
                        Context.LOCATION_SERVICE
                );
        //TODO handle different providers efficiently
        UserLocationMonitor monitor = new UserLocationMonitor(locationManager)
                .addProvider(NETWORK_PROVIDER)
                .addProvider(GPS_PROVIDER);
        return monitor;
    }
    private UserLocationMonitor(LocationManager locationManager){
        this.locationManager = locationManager;
        //Set the last known location as init value, might be out-of-date
        this.location = locationManager.getLastKnownLocation(GPS_PROVIDER);
    }

    private UserLocationMonitor addProvider(String provider){
        this.locationManager.requestLocationUpdates(provider,
                MIN_TIME,
                MIN_DISTANCE,
                this.userLocationListener);
        return this;
    }
    public Location getLocation(){
        return this.location != null ? new Location(this.location) : null;
    }

    private class UserLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            UserLocationMonitor.this.location = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}