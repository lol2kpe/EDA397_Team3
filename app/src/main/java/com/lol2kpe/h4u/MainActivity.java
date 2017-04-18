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
import com.lol2kpe.h4u.util.markers.MarkerOptionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private static String url = "http://lol2kpe.asuscomm.com/hospitals";
    Location location;
    Location locationMaps;
    LatLng myLocation;
    FloatingActionButton FAB;
    private GoogleMap mMap;
    private List<Hospital> hospitals;

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
    private View.OnClickListener FABonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.filter:
                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    startActivity(intent);
            }
        }
    };

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
    }

    public void addMapMarker(MarkerOptions marker) {
        LatLng destination = marker.getPosition();
        mMap.addMarker(marker.position(destination).title(marker.getTitle()).icon(marker.getIcon()));
    }

    public void removeAllMarkers() {
        mMap.clear();
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

        //VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }

}
