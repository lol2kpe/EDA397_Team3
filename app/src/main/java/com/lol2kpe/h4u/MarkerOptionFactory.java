package com.lol2kpe.h4u;

import android.location.Location;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by sam on 2017-04-04.
 */

public class MarkerOptionFactory{
    public static MarkerOptions getMarkerOptions(Hospital hospital){
        return new MarkerOptions()
                .title(hospital.getName())
                .position(
                        new LatLng(
                                hospital.getLatitude(),
                                hospital.getLongitude()
                        )
                )
                .icon(provideHospitalIcon());
    }
    public static MarkerOptions getMarkerOptions(UserLocation userLocation){
        return new MarkerOptions()
                .position(
                        new LatLng(
                                userLocation.getLatitude(),
                                userLocation.getLongitude()
                        )
                )
                .title(userLocation.getName());
    }
    private static BitmapDescriptor provideHospitalIcon(){
        return BitmapDescriptorFactory.fromResource(R.drawable.ic_local_hospital);
    }
}