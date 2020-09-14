package com.example.alevelapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private TextView eRegister;

    private int counter = 5;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.Name);
        ePassword = findViewById(R.id.Password);
        eAttemptsInfo = findViewById(R.id.NoAttempts);
        eLogin = findViewById(R.id.LoginBtn);
        eRegister = findViewById(R.id.tvRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = eName.getText().toString();
                String userPassword = ePassword.getText().toString();
                if (userName.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                } else {
                    isValid = validate(userName, userPassword);
                    if (!isValid) {
                        counter--;
                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(Login.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    } else {

                        startActivity(new Intent(Login.this, MainActivity.class));
                    }

                }
            }
        });
    }

    private boolean validate(String username, String userPassword) {

        if (Registration.credentials != null) {
            if (username.equals(Registration.credentials.getUsername()) && userPassword.equals(Registration.credentials.getPassword())) {
                return true;
            }
        }

        return false;
    }
}