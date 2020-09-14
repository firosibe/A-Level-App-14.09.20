package com.example.alevelapp;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import java.util.Locale;

public class Timer extends AppCompatActivity {
    private EditText EditText;
    private TextView ViewCountdown;
    private Button Set;
    private Button StartAndPause;
    private Button Reset;
    private Button button;

    private CountDownTimer CountDownTimer;

    private boolean TimerRunning;

    private long StartTime;
    private long TimeLeft;
    private long EndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText = findViewById(R.id.TimeInput);
        ViewCountdown = findViewById(R.id.CountdownTime);

        Set = findViewById(R.id.Set);
        StartAndPause = findViewById(R.id.StartAndPause);
        Reset = findViewById(R.id.Reset);

        button = (Button) findViewById(R.id.MainMenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openMain();
            }
        });

        Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = EditText.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(Timer.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(Timer.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                EditText.setText("");
            }
        });

        StartAndPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setTime(long milliseconds) {
        StartTime = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        EndTime = System.currentTimeMillis() + TimeLeft;

        CountDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                PauseAndStart();
            }
        }.start();

        TimerRunning = true;
        PauseAndStart();
    }

    private void pauseTimer() {
        CountDownTimer.cancel();
        TimerRunning = false;
        PauseAndStart();
    }

    private void resetTimer() {
        TimeLeft = StartTime;
        updateCountDown();
        PauseAndStart();
    }

    private void updateCountDown() {
        int hours = (int) (TimeLeft / 1000) / 3600;
        int minutes = (int) ((TimeLeft / 1000) % 3600) / 60;
        int seconds = (int) (TimeLeft / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        ViewCountdown.setText(timeLeftFormatted);
    }

    private void PauseAndStart() {
        if (TimerRunning) {
            EditText.setVisibility(View.INVISIBLE);
            Set.setVisibility(View.INVISIBLE);
            Reset.setVisibility(View.INVISIBLE);
            StartAndPause.setText("Pause");
        } else {
            EditText.setVisibility(View.VISIBLE);
            Set.setVisibility(View.VISIBLE);
            StartAndPause.setText("Start");

            if (TimeLeft < 1000) {
                StartAndPause.setVisibility(View.INVISIBLE);
            } else {
                StartAndPause.setVisibility(View.VISIBLE);
            }

            if (TimeLeft < StartTime) {
                Reset.setVisibility(View.VISIBLE);
            } else {
                Reset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", StartTime);
        editor.putLong("millisLeft", TimeLeft);
        editor.putBoolean("timerRunning", TimerRunning);
        editor.putLong("endTime", EndTime);

        editor.apply();

        if (CountDownTimer != null) {
            CountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        StartTime = prefs.getLong("startTimeInMillis", 600000);
        TimeLeft = prefs.getLong("millisLeft", StartTime);
        TimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDown();
        PauseAndStart();

        if (TimerRunning) {
            EndTime = prefs.getLong("endTime", 0);
            TimeLeft = EndTime - System.currentTimeMillis();

            if (TimeLeft < 0) {
                TimeLeft = 0;
                TimerRunning = false;
                updateCountDown();
                PauseAndStart();
            } else {
                startTimer();
            }
        }
    }
}