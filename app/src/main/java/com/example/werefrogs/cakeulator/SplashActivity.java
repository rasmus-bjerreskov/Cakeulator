package com.example.werefrogs.cakeulator;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * Here the splash screen leads into the main activity after the splash screen activity has finished
         */
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
// Splash screen was created using the tutorial from https://medium.com/@ssaurel/create-a-splash-screen-on-android-the-right-way-93d6fb444857