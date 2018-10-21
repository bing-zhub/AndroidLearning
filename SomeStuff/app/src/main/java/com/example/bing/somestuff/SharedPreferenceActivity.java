package com.example.bing.somestuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.animation.content.Content;

import java.io.IOException;
import java.util.ArrayList;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.bing.somestuff", Context.MODE_PRIVATE);

        ArrayList<String> languages = new ArrayList<String>();
        languages.add("C");
        languages.add("C++");
        languages.add("Python");
        languages.add("Java");
        try {
            sharedPreferences.edit().putString("languages", ObjectSerializer.serialize(languages)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<String> newLanguages = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("languages",ObjectSerializer.serialize(new ArrayList<String>())));
            Log.i("SharePre", newLanguages.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
