package com.example.org.orgd3if0074tubes1;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class Mode extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
