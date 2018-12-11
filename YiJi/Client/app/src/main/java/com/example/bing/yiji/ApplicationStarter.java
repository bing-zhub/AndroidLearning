package com.example.bing.yiji;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ApplicationStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("8zypnFERYw8X3snyaeWoddq0rCaI5B18hQcl7GsF")
                        .clientKey("jKWXBaoPN1f5zq7kQxUQCiHObLIOBmeHX7jDYdvW")
//                        .server("http://192.168.123.182:1337/parse/")
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
