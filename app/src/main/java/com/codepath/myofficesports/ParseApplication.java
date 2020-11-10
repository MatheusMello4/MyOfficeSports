package com.codepath.myofficesports;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HCHv89mRhStn6TwDZLEvIGPGIhpbp8xPIwaKVRSQ")
                .clientKey("hg24cWi2VuPrDxmjG7ohvI3AepIV19BcYcKIaUFk")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
