package com.lol2kpe.h4u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	static final int PICK_FILTER_OPTIONS_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Sets the customized menu_main as the app bar for this activity
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
		setSupportActionBar(toolbar);
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
		inflater.inflate(R.menu.menu_main, menu);

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
            case R.id.toolbar_action_filter:
            	Intent getFilterResults = new Intent(getApplicationContext(),
                        FilterActivity.class);
				startActivityForResult(getFilterResults, PICK_FILTER_OPTIONS_REQUEST );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request MainActivity is responding to
		if (requestCode == PICK_FILTER_OPTIONS_REQUEST) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				String returnValue = data.getStringExtra("type");
				Toast.makeText(getApplicationContext(),
						"Showing type: " + returnValue, Toast.LENGTH_SHORT).show();
				// TODO: Use filter data from FilterActivity to display proper items on the map
			}
		}
	}

}
