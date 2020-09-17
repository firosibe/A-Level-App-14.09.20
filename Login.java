package com.example.alevelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private CheckBox eRememberMe;
    private TextView eSignUp;

    private int counter = 5;

    String userName = "";
    String userPassword = "";

    boolean isValid = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.Name);
        ePassword = findViewById(R.id.Password);
        eAttemptsInfo = findViewById(R.id.NoAttempts);
        eLogin = (Button) findViewById(R.id.LoginBtn);
        eSignUp = findViewById(R.id.tvRegister);
        eRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null) {
            Map<String, ?> allEntries = sharedPreferences.getAll();

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Registration.credentials.addCredentials(entry.getKey(), entry.getValue().toString());
            }

            if(sharedPreferences.getBoolean("RememberMeCheckBox", false)){

                String savedUsername = sharedPreferences.getString("Username", "");
                String savedPassword = sharedPreferences.getString("Password", "");

                eName.setText(savedUsername);
                ePassword.setText(savedPassword);
                eRememberMe.setChecked(true);
            }
        }

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(Login.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = Registration.credentials.checkCredentials(userName, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (!isValid) {

                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(Login.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(Login.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Save the checkbox remember me state */
                        sharedPreferencesEditor.putBoolean("RememberMeCheckBox", eRememberMe.isChecked());

                        if(eRememberMe.isChecked()){
                            sharedPreferencesEditor.putString("Username", eName.getText().toString());
                            sharedPreferencesEditor.putString("Password", ePassword.getText().toString());
                        }

                        sharedPreferencesEditor.apply();

                        /* Allow the user in to your app by going into the next activity */
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }

                }
            }
        });

        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
    }
}