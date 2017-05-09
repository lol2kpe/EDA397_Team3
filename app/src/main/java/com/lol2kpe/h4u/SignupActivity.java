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
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button signup;
    EditText user;
    EditText email;
    EditText phone;
    EditText pass;
    EditText repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button) findViewById(R.id.signupbutton);
        user =  (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repassword);

        signup.setOnClickListener(loginOncLickListener);
    }

    private View.OnClickListener loginOncLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.signupbutton:
                    try {
                        if (!user.getText().toString().equals("") && !email.getText().toString().equals("") && !phone.getText().toString().equals("") && !pass.getText().toString().equals("") && !repass.getText().toString().equals("")) {
                            if ((pass.getText().toString()).equals(repass.getText().toString())) {
                                SharedPreferences sp = getSharedPreferences("Signup", Context.MODE_PRIVATE);
                                SharedPreferences.Editor Ed = sp.edit();
                                Ed.putString("User", user.getText().toString());
                                Ed.putString("Password", pass.getText().toString());
                                Ed.apply();

                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                Toast.makeText(SignupActivity.this, R.string.password_not, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, R.string.fill_in, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e){Toast.makeText(SignupActivity.this, R.string.fill_in, Toast.LENGTH_SHORT).show();}
            }
        }
    };

}

