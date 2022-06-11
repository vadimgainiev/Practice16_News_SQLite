package com.example.practice16_news_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Intent main = new Intent(this, MainActivity.class);
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                startActivity(main);
            }
        }.start();
    }
}
