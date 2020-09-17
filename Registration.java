package com.example.alevelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegPassword;

    public static Credentials credentials = new Credentials();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        Button eRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String registeredName = eRegName.getText().toString();
                String registeredPassword = eRegPassword.getText().toString();

                if(validate(registeredName, registeredPassword))
                {
                    credentials.addCredentials(registeredName, registeredPassword);

                    sharedPreferencesEditor.putString(registeredName, registeredPassword);
                    sharedPreferencesEditor.apply();

                    Toast.makeText(Registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Registration.this, MainActivity.class));
                }
            }
        });
    }

    boolean validate(String name, String password)
    {
        if(name.isEmpty() || (password.length() < 8))
        {
            Toast.makeText(this, "Please enter your name and ensure password is more than 8 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}