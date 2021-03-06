package com.lol2kpe.h4u;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by davidfogelberg on 2017-04-25.
 */


/*

Profile information: **************************

Name:

User Name:

Favorite locations:

Save function:


 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addListeners();

        Toast.makeText(ProfileActivity.this, "Profile", Toast.LENGTH_SHORT).show();

       // TextView view = (TextView) findViewById(R.id.viewLocation);
        //view.append("Favorite Hospital: " + );
    }

    public void saveLocation(String name, double latitude, double longitude) {

        /* Saves a users personal locations */

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addPlace(new DatabaseUserPlaces(name, latitude, longitude));

        Log.d("Reading: ", "Reading all places..");
        List<DatabaseUserPlaces> userPlaces = db.getAllPlaces();

        for (DatabaseUserPlaces pl : userPlaces) {
            String log = "Id: " + pl.getId() + " ,Name: " + pl.getName() + " ,Latitude: " + pl.getLatitude() + " ,Longitude: " + pl.getLongitude();
            Log.d("Name: ", log);

        }
    }

    public void deleteLocation(String name) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteRow(name);
    }

    public void addListeners(){
        final Button button = (Button) findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText text  = (EditText) findViewById(R.id.textLocation);
                saveLocation("" + text.getText(), 12.345000, 97.869440);
                Toast.makeText(ProfileActivity.this, "" + text.getText() , Toast.LENGTH_SHORT).show();
                TextView view = (TextView) findViewById(R.id.viewLocation);
                view.append("Favorite Hospital: " + text.getText());

            }
        });
    }
}
