package com.lol2kpe.h4u;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		UserLocationMonitor monitor = UserLocationMonitor.getMonitor(this.getApplicationContext());
		Location location = monitor.getLocation();
		Toast.makeText(getApplicationContext(),
				location.getLatitude() + "\n" + location.getLongitude(),
				Toast.LENGTH_LONG).show();
	}
}
