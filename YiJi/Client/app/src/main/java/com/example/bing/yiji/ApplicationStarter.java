package com.example.bing.yiji;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ApplicationStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("pZJJ7iSBoWNcN6sFYFvaFHSVX4RhBpKtk6E1AZti")
                        .clientKey("X5bmnE9IYdVnWd1Ps7Tn7p6sbEtIIP213JnT3tFx")
                        .server("http://10.0.2.2:1337/parse/") // for debug only! localhost
                        .build()
        );



        ParseUser.enableAutomaticUser();

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(acl, true);
    }
}
