package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Created by Jonathan Granström on 2017-04-02.
 */

public class FilterActivity extends AppCompatActivity {

    private Spinner spinnerType;    // The spinner menu for the "type" filter option

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Set the toolbar for the FilterActivity
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);

        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the spinner for the "type"-option (e.g., Health center, Dentist)
        spinnerType = (Spinner)findViewById(R.id.spinner_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_option_type_options, android.R.layout.simple_spinner_item);
        // Specifies the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(adapter);

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
                returnActivityResult(item.getActionView());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void clearFilter (MenuItem item) {
        Intent resultIntent = new Intent();
        try {
            resultIntent.putExtra("action", "reset");
            setResult(Activity.RESULT_OK, resultIntent);
        } catch (NullPointerException e) {
            Log.d("h4u", "NullPointerException caught during clearFilter action!");
            setResult(Activity.RESULT_CANCELED, resultIntent);
        }
        finish();

    }

    public void returnActivityResult(View view) {
        Intent resultIntent = new Intent();
        try {
            if (view.getId() == R.id.button_cancel) {
                setResult(Activity.RESULT_CANCELED, resultIntent);
            } else if (view.getId() == R.id.button_set) {
                String type = spinnerType.getSelectedItem().toString();
                resultIntent.putExtra("action", "set");
                resultIntent.putExtra("type", type);
                setResult(Activity.RESULT_OK, resultIntent);
            }
        } catch (NullPointerException e) {
            Log.d("h4u", "NullPointerException caught during returnActivityResult");
            setResult(Activity.RESULT_CANCELED, resultIntent);
        }
        finish();

    }

}
