package com.lol2kpe.h4u.util.markers;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Pharmacy;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.util.userlocation.UserLocation;

/**
 * Created by sam on 2017-04-04.
 */

public class MarkerOptionFactory {
    public static MarkerOptions getMarkerOptions(Place place) {
        MarkerOptions options = new MarkerOptions()
            .title(place.getName())
            .position(
                    new LatLng(
                            place.getLatitude(),
                            place.getLongitude()
                    )
            ).icon(getIcon(place));
        return options;
    }

    private static BitmapDescriptor getIcon(Place place) {
        if(place instanceof Hospital)
            return BitmapDescriptorFactory.fromResource(R.drawable.ic_local_hospital);
        if(place instanceof Pharmacy)
            // TODO: Icon for Pharmacy
            return BitmapDescriptorFactory.fromResource(R.drawable.ic_local_hospital);
        else
            return null;
    }

    public static MarkerOptions getMarkerOptions(UserLocation userLocation) {
        return new MarkerOptions()
                .position(
                        new LatLng(
                                userLocation.getLatitude(),
                                userLocation.getLongitude()
                        )
                )
                .title(userLocation.getName());
    }
}