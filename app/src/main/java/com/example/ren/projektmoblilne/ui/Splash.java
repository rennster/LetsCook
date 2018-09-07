package com.example.ren.projektmoblilne.ui;

/**
 * Created by ren on 24-May-17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ren.projektmoblilne.MainActivity;
import com.example.ren.projektmoblilne.R;


public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
