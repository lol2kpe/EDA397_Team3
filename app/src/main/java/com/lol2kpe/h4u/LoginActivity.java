package com.lol2kpe.h4u;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String storedUser;
    String storedPass;

    EditText user;
    EditText pass;
    CheckBox saveCredentials;
    Button login;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        saveCredentials = (CheckBox) findViewById(R.id.saveCredentials);
        fab = (FloatingActionButton) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(loginOncLickListener);
        fab.setOnClickListener(fabOncLickListener);

        SharedPreferences sp1 = getSharedPreferences("LoginSaved", Context.MODE_PRIVATE);

        storedUser = sp1.getString("UserSaved", null);
        storedPass = sp1.getString("PasswordSaved", null);

        if ((storedUser != "") && (storedPass != "")) {
            user.setText(storedUser);
            pass.setText(storedPass);
            saveCredentials.setChecked(true);
        }

    }

    private View.OnClickListener loginOncLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login:

                    try {
                        if ((user.getText().toString().equals("") || (pass.getText().toString().equals("")))) {
                            Toast.makeText(LoginActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        SharedPreferences sp1 = getSharedPreferences("Signup", Context.MODE_PRIVATE);
                        storedUser = sp1.getString("User", null);
                        storedPass = sp1.getString("Password", null);

                        if ((!storedUser.equals(user.getText().toString())) || (!storedPass.equals(pass.getText().toString()))) {
                            Toast.makeText(LoginActivity.this, "Incorrect user or password", Toast.LENGTH_SHORT).show();
                        } else {
                            if (saveCredentials.isChecked()) {
                                SharedPreferences sp = getSharedPreferences("LoginSaved", Context.MODE_PRIVATE);
                                SharedPreferences.Editor Ed = sp.edit();
                                Ed.putString("UserSaved", user.getText().toString());
                                Ed.putString("PasswordSaved", pass.getText().toString());
                                Ed.apply();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                SharedPreferences sp = getSharedPreferences("LoginSaved", Context.MODE_PRIVATE);
                                SharedPreferences.Editor Ed = sp.edit();
                                Ed.putString("UserSaved", "");
                                Ed.putString("PasswordSaved", "");
                                Ed.apply();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            }
                        }
                    }catch (Exception e) {}
            }
        }
    };

    private View.OnClickListener fabOncLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.signup:
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
            }
        }
    };
}

