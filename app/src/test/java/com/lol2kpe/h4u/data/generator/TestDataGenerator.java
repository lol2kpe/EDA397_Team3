package com.lol2kpe.h4u.data.generator;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.lol2kpe.h4u.data.model.Place;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by sam on 2017-04-20.
 */

public class TestDataGenerator {
    @Test
    public void testNumberOfElements() throws FileNotFoundException {
        final int numberOfElements = 100;
        DataGenerator dg = new DataGenerator().setNumberOfElements(100);
        List<Place> places = new ArrayList<>();
        for (Place place : dg){
            places.add(place);
        }
        assertEquals(places.size(), numberOfElements);
    }

}
