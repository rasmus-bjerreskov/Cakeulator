package com.example.werefrogs.cakeulator;

import android.app.Application;
import android.os.SystemClock;

public class SplashActivity_Sleep extends Application {
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
// Splash screen was created using the tutorial from https://medium.com/@ssaurel/create-a-splash-screen-on-android-the-right-way-93d6fb444857
