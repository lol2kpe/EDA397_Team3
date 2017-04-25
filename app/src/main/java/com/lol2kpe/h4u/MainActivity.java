package com.lol2kpe.h4u;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.data.model.Pharmacy;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.util.markers.MarkerOptionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    static final int FILTER_ACTIVITY_REQUEST = 1;

    private static String url = "http://lol2kpe.asuscomm.com:3001/hospitals.json";

    Location location;
    Location locationMaps;
    LatLng myLocation;
    FloatingActionButton FAB;
    private GoogleMap mMap;
    private List<Hospital> hospitals;

    private List<Place> places;
    final GsonRequest gsonRequest = new GsonRequest(url, Place[].class, null, new Response.Listener<Place[]>() {

        @Override
        public void onResponse(Place[] placesResponse) {
            places = Arrays.asList(placesResponse);
            Toast.makeText(MainActivity.this, "Places are refreshed", Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
            Toast.makeText(MainActivity.this, "Database error", Toast.LENGTH_SHORT).show();
        }
    });
    private View.OnClickListener FABonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.filter:

                    // TODO: Remove once the database/place arraylist works

                    ArrayList<Place> tempPlaces = new ArrayList<>();

                    tempPlaces.add(new Hospital()
                            .setName("Sahlgrenska")
                            .setRating(5)
                            .setLatitude(57.703830518)
                            .setLongitude(11.93582959)
                    );
                    tempPlaces.add(new Hospital()
                            .setName("Lundby")
                            .setRating(3)
                            .setLatitude(57.707663836)
                            .setLongitude(11.90916303)
                    );
                    tempPlaces.add(new Pharmacy()
                            .setName("Kungens Apotek")
                            .setRating(4)
                            .setLatitude(57.707833836)
                            .setLongitude(11.97916303)
                    );
                    tempPlaces.add(new Pharmacy()
                            .setName("Kronans Apotek")
                            .setRating(2)
                            .setLatitude(57.687833836)
                            .setLongitude(11.97916303)
                    );


                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    intent.putExtra(getResources().getString(R.string.object_data),
                            new ArrayList<>(tempPlaces));
                    startActivityForResult(intent, FILTER_ACTIVITY_REQUEST);
            }
        }
    };

    @Override
    @SuppressWarnings({"unchecked"})
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check if result comes from FilterActivity
        if (requestCode == FILTER_ACTIVITY_REQUEST) {
            // Check if request was successful/had results
            if(resultCode == RESULT_OK) {
                if(checkFilteredData(data)) {
                    ArrayList<Place> objects = (ArrayList<Place>) data
                            .getSerializableExtra(getResources().getString(R.string.filtered_data));
                    if (objects.isEmpty()) {
                        removeAllMarkers();
                        Toast.makeText(this, getResources().getString(R.string.no_results),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        addMapMarkers(objects);
                        Toast.makeText(this, getResources().getString(R.string.results),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        // Replace the app bar with a custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        // Create a Floating Action Button (FAB) which starts the FilterActivity
        FAB = (FloatingActionButton) findViewById(R.id.filter);
        FAB.setOnClickListener(FABonClickListener);

        hospitals = new ArrayList<>();
        fetchData();

        /********** MAP **********/

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        // Location of the user
        /*UserLocationMonitor monitor = UserLocationMonitor.getMonitor(this.getApplicationContext());
        location = monitor.getLocation();*/

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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

        } else if (id == R.id.med_reminders) {

        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        addMapMarkers(new ArrayList<>(this.placeData));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.7058666,11.9592284), 10.0f));
    }

    private void addMapMarkers(ArrayList<Place> objects) {
        removeAllMarkers();
        for (Place place : objects) {
            MarkerOptions marker = MarkerOptionFactory.getMarkerOptions(place);
            LatLng destination = marker.getPosition();
            mMap.addMarker(marker.position(destination)
                    .title(marker.getTitle()).icon(marker.getIcon()));
        }
    }

    public void removeAllMarkers() {
        mMap.clear();
    }

    private boolean checkFilteredData(Intent intent) {
        // Get a map of extended data from the intent
        Bundle extras = intent.getExtras();
        // Check if the data from MainActivity is null
        if (extras == null) {
            Log.w("NullIntentData", "The intent data received in MainActivity is null");
            return false;
        }
        // Check if the data from MainActivity is empty
        if(extras.isEmpty()) {
            Log.w("EmptyIntentData", "The intent data received in MainActivity is empty. " +
                    "Empty bundle: " + extras.isEmpty());
            return false;
        }
        // Check if the name of the data is the same as the expected name
        if(!extras.containsKey(getResources().getString(R.string.filtered_data))) {
            Set keys = extras.keySet();
            StringBuilder sb = new StringBuilder();
            Iterator i = keys.iterator();
            while (i.hasNext()) {
                String key = (String)i.next();
                sb.append(" " + key);
            }
            Log.e("IntentDataKeyMissing", "Could not find the needed intent data for the" +
                    " FilterActivity. Expected data name: " +
                    getResources().getString(R.string.filtered_data) +
                    ", Found data names: " + sb.toString());
            return false;
        }
        return true;
    }

    // fetches data from server
    public void fetchData() {

        Hospital sahlgrenska = new Hospital()
                .setName("Sahlgrenska")
                .setId(10)
                .setHospitalType("Emergency")
                .setLatitude(57.703830518)
                .setLongitude(11.93582959);
        Hospital lundby = new Hospital()
                .setName("Lundby")
                .setId(10)
                .setHospitalType("Regular appointments")
                .setLatitude(57.707663836)
                .setLongitude(11.90916303);
        hospitals.add(sahlgrenska);
        hospitals.add(lundby);

        // uncomment this, and database should work
       // VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }

}
