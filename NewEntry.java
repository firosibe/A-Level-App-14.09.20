package com.example.alevelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewEntry extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        button = (Button) findViewById(R.id.MainMenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        button = (Button) findViewById(R.id.BloodGlucoseLevels);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBGL();
            }
        });
        button = (Button) findViewById(R.id.FoodAndDrinks);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFAD();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openBGL() {
        Intent intent = new Intent(this, BloodGlucoseLevels.class);
        startActivity(intent);
    }

    public void openFAD() {
        Intent intent = new Intent(this, FoodAndDrinks.class);
        startActivity(intent);
    }
}