package com.weijiadev.googlex.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.weijiadev.googlex.R;


public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("tag","tab1");
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

    }

}
