package com.lol2kpe.h4u.data;

import android.util.Log;

import com.lol2kpe.h4u.DatabaseHandler;
import com.lol2kpe.h4u.DatabaseUserPlaces;
import com.lol2kpe.h4u.data.model.Employee;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-20.
 */

public class TestLocalDatabase {





    @Test
    public void testGetName() throws Exception {


        // insert my personal places for profile... test code //


     /*   DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ..");
        db.addPlace(new DatabaseUserPlaces("Sahlgrenska", 12.345000, 97.869440));

        Log.d("Reading: ", "Reading all places..");
        List<DatabaseUserPlaces> userPlaces = db.getAllPlaces();

        for (DatabaseUserPlaces pl : userPlaces) {
            String log = "Id: " + pl.getId() + " ,Name: " + pl.getName() + " ,Latitude: " + pl.getLatitude() + " ,Longitude: " + pl.getLongitude();
            Log.d("Name: ", log);

        }

     */


    }
}
