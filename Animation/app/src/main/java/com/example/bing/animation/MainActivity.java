package com.example.bing.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView homerImageView, bartImageView;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bartImageView = findViewById(R.id.bartImageView);
        homerImageView = findViewById(R.id.homerImageView);
        bartImageView.setX(-1000);
        bartImageView.animate().translationXBy(2000).setDuration(20000);
//        bartImageView.animate().translationXBy(-200).setDuration(2000);
//        bartImageView.animate().translationXBy(200).setDuration(2000);

    }

    public void fade(View view){
        if(flag){
//            bartImageView.animate().translationYBy(-1000).setDuration(1000);
//            bartImageView.animate().alpha(0).setDuration(2000);
//            homerImageView.animate().alpha(1).setDuration(2000);
            flag = false;
//            bartImageView.animate().rotation(18000000).setDuration(100000); //让辛普森旋转
//            bartImageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        }else{
//            bartImageView.animate().translationYBy(1000).setDuration(1000);
//            homerImageView.animate().alpha(0).setDuration(2000);
//            bartImageView.animate().alpha(1).setDuration(2000);
//            bartImageView.animate().rotation(-1800).setDuration(1000);
//            bartImageView.animate().scaleY(2).scaleX(2).setDuration(2000);
            flag = true;
        }
    }
}
