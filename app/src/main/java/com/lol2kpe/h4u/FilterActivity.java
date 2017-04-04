package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;

import static android.R.id.edit;

/**
 * Created by Jonathan Granström on 2017-04-02.
 */

public class FilterActivity extends AppCompatActivity {

    public static final String FILTER_PREFERENCES = "filterpref";

    private Spinner spinnerType;    // The spinner menu for the "type" filter option
    private ArrayAdapter<CharSequence> adapter;

    SharedPreferences filterPreferences;
    SharedPreferences.Editor filterPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Set the toolbar for the FilterActivity
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);

        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the shared preference file and editor
        filterPreferences = getSharedPreferences(FILTER_PREFERENCES, MODE_PRIVATE );
        filterPrefEditor = filterPreferences.edit();

        // Set the spinner for the "type"-option (e.g., Health center, Dentist)
        spinnerType = (Spinner)findViewById(R.id.spinner_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_option_type_options, android.R.layout.simple_spinner_item);
        // Specifies the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(adapter);

        // Setup the filter options
        setInitFilterOptions();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_clear_filter:
                clearFilter(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void setInitFilterOptions() {
        if(filterPreferences.contains("type")) {
            spinnerType.setSelection(adapter.
                    getPosition(filterPreferences.getString("type", "")));
        } else {
            spinnerType.setSelection(0);
        }
    }

    public void clearFilter (MenuItem item) {
        Intent resultIntent = new Intent();
        try {
            if(item.getItemId() == R.id.toolbar_action_clear_filter) {
                filterPrefEditor.clear();
                filterPrefEditor.commit();
                resultIntent.putExtra("status", "reset");
                setResult(Activity.RESULT_OK, resultIntent);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            Log.d("h4u",
                    "IOException caught! clearFilter action wasn't called by clear filter button!");
            setResult(Activity.RESULT_CANCELED, null);
            finish();
        }
        finish();
    }

    public void setFilter (View view) {
        Intent resultIntent = new Intent();
        try {
            if(view.getId() == R.id.button_cancel) {
                setResult(Activity.RESULT_CANCELED, null);
            } else if(view.getId() == R.id.button_set) {
                filterPrefEditor.putString("type",
                        spinnerType.getSelectedItem().toString());
                filterPrefEditor.commit();
                resultIntent.putExtra("status", "set");
                resultIntent.putExtra("type",
                        filterPreferences.getString("type", ""));
                setResult(Activity.RESULT_OK, resultIntent);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            Log.d("h4u", "IOException caught! setFilter recieved wrong view!");
            setResult(Activity.RESULT_CANCELED, null);
            finish();
        }
        finish();
    }

}
