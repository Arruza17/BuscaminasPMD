package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Add the animations
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.go_up);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.go_down);

        TextView textViewLogoIntro = findViewById(R.id.textViewLogoIntro);
        ImageView imageLogoIntro = findViewById(R.id.imageLogoIntro);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        final int threeSeconds = 3000;
        CountDownTimer countDownTimer = new CountDownTimer(threeSeconds,10) {
            @Override
            public void onTick(long l) {
                long finishedSeconds = threeSeconds - l;
                int total = (int) (((float)finishedSeconds/(float) threeSeconds)*100.0);
                progressBar.setProgress(total);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        textViewLogoIntro.setAnimation(animation2);
        imageLogoIntro.setAnimation(animation1);
        progressBar.setAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        },4000);

    }
}