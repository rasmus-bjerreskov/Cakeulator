package com.example.werefrogs.cakeulator;

import android.app.Application;
import android.os.SystemClock;

public class SplashActivity_Sleep extends Application {
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
