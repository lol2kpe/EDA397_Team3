package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.io.IOException;

/**
 * Created by Jonathan Granström on 2017-04-02.
 */

public class FilterActivity extends AppCompatActivity {

    private Spinner spinnerType;    // The spinner menu for the "type" filter option
    private ArrayAdapter<CharSequence> adapter;

    SharedPreferences filterPreferences;
    SharedPreferences.Editor filterPrefEditor;

    Intent resultIntentData;

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
        filterPreferences = getSharedPreferences(
                getResources().getString(R.string.filter_preferences), MODE_PRIVATE);
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

        // Set the initial filter selections for the Activity
        setFilterSelections();

        // Initialize the return Intent
        resultIntentData = new Intent();

    }

    /**
     * Method overrides the standard onCreateOptionsMenu. Makes sure the layout for the custom
     * toolbar is used as the menu for the activity.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);

    }

    /**
     * Method is called when an menu item on the toolbar is selected (clicked on). Method receives
     * the item clicked and uses the proper case. If no case could be found for the item,
     * the super class is called to handle that situation.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_clear_filter:
                clearFilterPreferences(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Sets the initial filter options for the activity. If no saved user choice for a filter option
     * exists in the SharedPreferences xml-file, the options are set to their default values.
     * If saved user options exists for a filter option, the filter option is set to whatever value
     * the user has chosen.
     */
    private void setFilterSelections() {
        if(filterPreferences.contains(getResources().getString(R.string.filter_preferences_type))) {
            spinnerType.setSelection(adapter.getPosition(filterPreferences.getString(
                    getResources().getString(R.string.filter_preferences_type), "")));
        } else {
            spinnerType.setSelection(0);
        }
    }

    /**
     * Cancels the FilterActivity and returns the user to the MainActivity.
     * @param view
     */
    public void cancelFilterActivity (View view) {
        try {
            if (view.getId() == R.id.button_cancel) {
                setResult(Activity.RESULT_CANCELED, null);
                finish();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log.d("h4u", "cancelFilterActivity was not called by the proper UI element! " +
                    "Expected call from action: " + R.id.button_cancel);
            setResult(Activity.RESULT_CANCELED, null);
            finish();
        }
    }

    /**
     * Writes the user's chosen filter options to the SharedPreferences xml-file.
     * @param view
     */
    public void setFilterPreferences (View view) {
        try {
            if(view.getId() == R.id.button_set) {
                filterPrefEditor.putString(getResources().getString(
                        R.string.filter_preferences_type), spinnerType.getSelectedItem().toString());
                filterPrefEditor.commit();
                setResult(Activity.RESULT_OK, null);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            Log.d("h4u", "setFilterOptions was not called by the proper UI element! " +
                    "Expected call from action: " + R.id.button_set);
            setResult(Activity.RESULT_CANCELED, null);
            finish();
        }
        finish();
    }

    /**
     * Clears the SharedPreference xml-file of any saved user preferences.
     * @param item
     */
    public void clearFilterPreferences (MenuItem item) {
        filterPrefEditor.clear();
        filterPrefEditor.commit();
        setResult(Activity.RESULT_CANCELED, null);
        finish();
    }

}
