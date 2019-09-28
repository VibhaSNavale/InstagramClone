package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4pLMUQpVkLA8IE7w3mSbvzAPaAhNJ1LDxermH0a1")
                // if defined
                .clientKey("j9Z83L7JFCFVXANmV1QLljqUSpktztjMprT3HaIV")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
