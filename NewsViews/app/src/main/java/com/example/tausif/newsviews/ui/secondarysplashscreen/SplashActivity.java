package com.example.tausif.newsviews.ui.secondarysplashscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.ui.main.MainActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
