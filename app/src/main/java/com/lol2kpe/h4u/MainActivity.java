package com.lol2kpe.h4u;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		UserLocationMonitor monitor = UserLocationMonitor.getMonitor(this);
		Location location;
		while((location = monitor.getLocation()) == null);
		Toast.makeText(getApplicationContext(),
				location.getLatitude() + "\n" + location.getLongitude(),
				Toast.LENGTH_LONG).show();
	}
}
