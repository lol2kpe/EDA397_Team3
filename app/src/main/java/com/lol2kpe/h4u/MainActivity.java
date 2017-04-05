package com.lol2kpe.h4u;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lol2kpe.h4u.userlocation.UserLocationMonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private List<Hospital> hospitals;
	private static String url = "http://lol2kpe.asuscomm.com/hospitals";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hospitals = new ArrayList<>();
		fetchData();

	}

	// fetches data from server
	public List<Hospital>fetchData() {

		Hospital sahlgrenska = new Hospital("Sahlgrenska",5,"Emergency", 57.703830518, 11.93582959, 10,"00:00 - 24:00","Göteborg 41753", "075-8833865");
		Hospital lundby = new Hospital("Lundby",5,"Regular appointments", 57.707663836 , 11.90916303, 10,"08:00 - 22:00","Göteborg 41753", "075-8866465");

		hospitals.add(sahlgrenska);
		hospitals.add(lundby);

		return hospitals;

		//VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
	}

	final GsonRequest gsonRequest = new GsonRequest(url, Hospital[].class, null, new Response.Listener<Hospital[]>() {

		@Override
		public void onResponse(Hospital[] hospitalsResponse) {
			hospitals = Arrays.asList(hospitalsResponse);
			Toast.makeText(MainActivity.this, "Hospitals refreshed", Toast.LENGTH_SHORT).show();
		}
	}, new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError volleyError) {
			//if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
			Toast.makeText(MainActivity.this, "Database error", Toast.LENGTH_SHORT).show();
		}
	});


}
