package com.example.bing.videoandsound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShowAndHide extends AppCompatActivity {

    ImageView  imgView;

    void show(View view){
        imgView.setVisibility(View.VISIBLE);
    }

    void hide(View view){
        imgView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and_hide);

        imgView = findViewById(R.id.imageView);
    }
}
