package com.example.bms.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bms.R;

public class SplashScreenActivity extends AppCompatActivity {

    final private static int TIME_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //clean code Intent
                startActivity(new Intent(SplashScreenActivity.this, GetStartedActivity.class));
                finish();
            }
        }, TIME_SPLASH);
    }
}
