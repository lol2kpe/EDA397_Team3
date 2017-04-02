package com.lol2kpe.h4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Sets the customized menu_main as the app bar for this activity
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);

		return super.onCreateOptionsMenu(menu);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_filter:
                startActivity(new Intent(getApplicationContext(), FilterActivity.class));
                //showFilterDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void showFilterDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.dialog_filter_title);

        final ArrayList<Integer> selectedItemsIndexList = new ArrayList<>();

        // TODO: Add ACTUAL filter options
        // TODO: Add possible functionality which saves selected filter options

        // This is just a test/example of filter option
        alertDialogBuilder.setMultiChoiceItems(R.array.dialog_filter_options, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedItemsIndexList.add(which);
                        } else if (selectedItemsIndexList.contains(which)) {
                            selectedItemsIndexList.remove(Integer.valueOf(which));
                        }
                    }
                });

        String positiveText = getString(R.string.dialog_filter_ok);
        alertDialogBuilder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Create functionality which send filter options to database(?)
                Toast.makeText(getApplicationContext(), "Filter set", Toast.LENGTH_SHORT).show();
            }
        });

        String negativeText = getString(R.string.dialog_filter_cancel);
        alertDialogBuilder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Create method which resets the chosen filter options, and sends this to the database(?)
                Toast.makeText(getApplicationContext(), "Filter cleared", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
	}
}
