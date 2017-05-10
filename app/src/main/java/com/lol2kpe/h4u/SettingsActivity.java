package com.lol2kpe.h4u;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button changeLanguage;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changeLanguage = (Button) findViewById(R.id.changeLan);
        changeLanguage.setOnClickListener(languageOncLickListener);

        save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(saveOncLickListener);
    }

    private View.OnClickListener languageOncLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.changeLan:
                    showDialog(0);
            }
        }
    };

    private View.OnClickListener saveOncLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.saveButton:
                    Intent launcher = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(launcher);
                    finishAffinity();
            }
        }
    };

    @Override
    public Dialog onCreateDialog(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (id == 0) {
            builder.setTitle(R.string.dialogLanguage).setItems(R.array.languagesArray, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        SharedPreferences local = getSharedPreferences("Lan", Context.MODE_PRIVATE);
                        SharedPreferences.Editor Ed = local.edit();
                        Ed.putString("Language", "en");
                        Ed.apply();
                    }
                    if (which == 1) {
                        SharedPreferences local = getSharedPreferences("Lan", Context.MODE_PRIVATE);
                        SharedPreferences.Editor Ed = local.edit();
                        Ed.putString("Language", "es");
                        Ed.apply();
                    }
                    if (which == 2) {
                        SharedPreferences local = getSharedPreferences("Lan", Context.MODE_PRIVATE);
                        SharedPreferences.Editor Ed = local.edit();
                        Ed.putString("Language", "sv");
                        Ed.apply();
                    }
                }
            });
        }
        return builder.create();
    }

}
