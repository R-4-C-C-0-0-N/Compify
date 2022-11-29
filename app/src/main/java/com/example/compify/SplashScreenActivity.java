package com.example.compify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView splashScreen;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    float translationY = 1400f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initActivity();
        doSplash();
    }

    private void initActivity() {
        splashScreen = findViewById(R.id.SplashScreen);
        splashScreen.setAlpha(0f);
        splashScreen.setTranslationY(translationY);

        MediaPlayer mediaPlayer = MediaPlayer.create(SplashScreenActivity.this,R.raw.startup_sound);
        mediaPlayer.start();
    }

    private void doSplash() {
        splashScreen.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(2500).start();

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}