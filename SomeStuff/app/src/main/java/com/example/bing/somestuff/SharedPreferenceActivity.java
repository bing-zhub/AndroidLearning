package com.example.bing.somestuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.animation.content.Content;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.bing.somestuff", Context.MODE_PRIVATE);
//        sharedPreferences.edit().putString("username", "admin").apply();

        String username = sharedPreferences.getString("username","");
        Log.i("SharePre",username);
    }
}
