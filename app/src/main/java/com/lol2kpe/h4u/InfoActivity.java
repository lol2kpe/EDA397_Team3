package com.lol2kpe.h4u;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lol2kpe.h4u.data.model.Place;

public class InfoActivity extends AppCompatActivity {
    private Place place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.place = (Place) getIntent().getExtras().get("object");
        setContentView(R.layout.activity_info);
        setInformation();
        setTitle(place.getName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?f=d&daddr=" + place.getLatitude() + "," + place.getLongitude()));
                intent.setComponent(new ComponentName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity"));
                startActivity(intent);
            }
        });
    }

    private void setInformation() {
        setAddress();
        setPhoneNumber();
        setRating();
        setOpeningHours();
    }

    private void setOpeningHours() {
        String openField = "CLOSED NOW";
        /*
            TODO openinghours compabillity
        if(this.place.getOpeningHours().isOpen()) {
            openField = "OPEN NOW";
        }
        */
        changeTextField((TextView) findViewById(R.id.openinghour), openField);
    }

    private void setRating() {
        changeTextField((TextView) findViewById(R.id.rating), String.valueOf(this.place.getRating()));
    }

    private void setPhoneNumber() {
        changeTextField((TextView) findViewById(R.id.phonenumber), this.place.getPhoneNumber());
    }

    private void setAddress() {
        changeTextField((TextView) findViewById(R.id.address), this.place.getAddress());
    }

    private void changeTextField(TextView textView, String content) {
        textView.append("\t" + content);
    }
}