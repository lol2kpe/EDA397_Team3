package com.lol2kpe.h4u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;

public class LauncherActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO: Make into LAUNCHER ACTIVITY
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				//set the new Content of your activity
				//MainActivity.this.setContentView(R.layout.activity_main);
				Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
				startActivity(intent);
				finishAffinity();
			}
		}.start();
	}
}
