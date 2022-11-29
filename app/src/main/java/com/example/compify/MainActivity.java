package com.example.compify;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button menuButton, cpuButton, gpuButton, ramButton, ssdButton;
    TextView cpuTextView, gpuTextView, ramTextView, ssdTextView;
    float translationX1 = 100f, translationX2 = -100f, translationY1 = 100f, translationY2 = -100f;
    boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    private void initActivity() {
        menuButton = findViewById(R.id.MenuButton);
        cpuButton = findViewById(R.id.CPU);
        gpuButton = findViewById(R.id.GPU);
        ramButton = findViewById(R.id.RAM);
        ssdButton = findViewById(R.id.SSD);

        cpuTextView = findViewById(R.id.CpuText);
        gpuTextView = findViewById(R.id.GpuText);
        ramTextView = findViewById(R.id.RamText);
        ssdTextView = findViewById(R.id.SsdText);

        cpuButton.setAlpha(0f);
        gpuButton.setAlpha(0f);
        ramButton.setAlpha(0f);
        ssdButton.setAlpha(0f);

        cpuTextView.setAlpha(0f);
        gpuTextView.setAlpha(0f);
        ramTextView.setAlpha(0f);
        ssdTextView.setAlpha(0f);

        cpuButton.setTranslationX(translationX1);
        gpuButton.setTranslationX(translationX2);
        ramButton.setTranslationX(translationX2);
        ssdButton.setTranslationX(translationX1);

        cpuButton.setTranslationY(translationY1);
        gpuButton.setTranslationY(translationY1);
        ramButton.setTranslationY(translationY2);
        ssdButton.setTranslationY(translationY2);

        menuButton.setOnClickListener(this);
        cpuButton.setOnClickListener(this);
        gpuButton.setOnClickListener(this);
        ramButton.setOnClickListener(this);
        ssdButton.setOnClickListener(this);

        cpuButton.setClickable(false);
        gpuButton.setClickable(false);
        ramButton.setClickable(false);
        ssdButton.setClickable(false);
    }

    private void menuButtonPressed() {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.menu_button_sound);
        mediaPlayer.start();
        if(isMenuOpen) {
            closeMenu();
        }else {
            openMenu();
        }
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        cpuButton.animate().translationX(0f).translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
            public void run(){
                cpuTextView.setAlpha(1f);
                cpuButton.setClickable(false);
            }
        }).withEndAction(new Runnable(){
            public void run(){
                gpuButton.animate().translationX(0f).translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
                    public void run(){
                        gpuTextView.setAlpha(1f);
                        gpuButton.setClickable(false);
                    }
                }).withEndAction(new Runnable(){
                    public void run(){
                        cpuButton.setClickable(true);
                        gpuButton.setClickable(true);
                    }
                }).start();
            }
        }).start();
        ramButton.animate().translationX(0f).translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
            public void run(){
                ramTextView.setAlpha(1f);
                ramButton.setClickable(false);
            }
        }).withEndAction(new Runnable(){
            public void run(){
                ssdButton.animate().translationX(0f).translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
                    public void run(){
                        ssdTextView.setAlpha(1f);
                        ssdButton.setClickable(false);
                    }
                }).withEndAction(new Runnable(){
                    public void run(){
                        ramButton.setClickable(true);
                        ssdButton.setClickable(true);
                    }
                }).start();
            }
        }).start();
    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        gpuButton.animate().translationX(translationX2).translationY(translationY1).alpha(0f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
            public void run(){
                gpuButton.setClickable(false);
            }
        }).withEndAction(new Runnable(){
            public void run(){
                gpuButton.setClickable(false);
                cpuButton.animate().translationX(translationX1).translationY(translationY1).alpha(0f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
                    public void run(){
                        cpuButton.setClickable(false);
                    }
                }).withEndAction(new Runnable(){
                    public void run(){
                        cpuButton.setClickable(false);
                    }
                }).start();
            }
        }).start();
        ssdButton.animate().translationX(translationX1).translationY(translationY2).alpha(0f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
            public void run(){
                ssdButton.setClickable(false);
            }
        }).withEndAction(new Runnable(){
            public void run(){
                ssdButton.setClickable(false);
                ramButton.animate().translationX(translationX2).translationY(translationY2).alpha(0f).setInterpolator(interpolator).setDuration(700).withStartAction(new Runnable(){
                    public void run(){
                        ramButton.setClickable(false);
                    }
                }).withEndAction(new Runnable(){
                    public void run(){
                        ramButton.setClickable(false);
                    }
                }).start();
            }
        }).start();

        cpuTextView.setAlpha(0f);
        gpuTextView.setAlpha(0f);
        ramTextView.setAlpha(0f);
        ssdTextView.setAlpha(0f);
    }

    private void cpuButtonPressed() {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.button_sound);
        mediaPlayer.start();
        try {
            if(isMenuOpen) {
                closeMenu();
            }else {
                openMenu();
            }
            path = "cpu";
            Intent intent = new Intent(MainActivity.this, PartsPickerActivity.class);
            intent.putExtra("path", path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gpuButtonPressed() {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.button_sound);
        mediaPlayer.start();
        try {
            if(isMenuOpen) {
                closeMenu();
            }else {
                openMenu();
            }
            path = "gpu";
            Intent intent = new Intent(MainActivity.this, PartsPickerActivity.class);
            intent.putExtra("path", path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ramButtonPressed() {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.button_sound);
        mediaPlayer.start();
        try {
            if(isMenuOpen) {
                closeMenu();
            }else {
                openMenu();
            }
            path = "ram";
            Intent intent = new Intent(MainActivity.this, PartsPickerActivity.class);
            intent.putExtra("path", path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ssdButtonPressed() {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.button_sound);
        mediaPlayer.start();
        try {
            if(isMenuOpen) {
                closeMenu();
            }else {
                openMenu();
            }
            path = "ssd";
            Intent intent = new Intent(MainActivity.this, PartsPickerActivity.class);
            intent.putExtra("path", path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MenuButton:
                menuButtonPressed();
                break;
            case R.id.CPU:
                cpuButtonPressed();
                break;
            case R.id.GPU:
                gpuButtonPressed();
                break;
            case R.id.RAM:
                ramButtonPressed();
                break;
            case R.id.SSD:
                ssdButtonPressed();
                break;
        }
    }
}