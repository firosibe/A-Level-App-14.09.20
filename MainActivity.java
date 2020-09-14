package com.example.alevelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.NewEntry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewEntry();
            }
        });
        button = (Button) findViewById(R.id.CarbCounter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarbCounter();
            }
        });
        button = (Button) findViewById(R.id.Graph);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGraph();
            }
        });
        button = (Button) findViewById(R.id.History);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });
        button = (Button) findViewById(R.id.Timer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimer();
            }
        });button = (Button) findViewById(R.id.Settings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
        button = (Button) findViewById(R.id.Help);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelp();
            }
        });
        button = (Button) findViewById(R.id.Print);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrint();
            }
        });
        button = (Button) findViewById(R.id.Share);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShare();
            }
        });
        button = (Button) findViewById(R.id.Account);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });
        button = (Button) findViewById(R.id.Logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogout();
            }
        });
    }

    public void openNewEntry() {
        Intent intent = new Intent(this, NewEntry.class);
        startActivity(intent);
    }
    public void openCarbCounter() {
        Intent intent = new Intent(this, CarbCounter.class);
        startActivity(intent);
    }
    public void openGraph(){
        Intent intent = new Intent(this, Graph.class);
        startActivity(intent);
    }
    public void openHistory(){
            Intent intent = new Intent(this, History.class);
            startActivity(intent);
        }
    public void openTimer(){
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
    public void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openHelp(){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
    public void openPrint(){
        Intent intent = new Intent(this, Print.class);
        startActivity(intent);
    }
    public void openShare(){
        Intent intent = new Intent(this, Share.class);
        startActivity(intent);
    }
    public void openAccount(){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }
    public void openLogout(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}