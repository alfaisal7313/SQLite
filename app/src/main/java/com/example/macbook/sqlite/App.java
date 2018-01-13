package com.example.macbook.sqlite;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by MacBook on 1/9/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

    }
}
