package com.example.bing.videoandsound;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

//        final Handler handler = new Handler();
//
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Hey", "A s has passed by");
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        handler.post(run);

        new CountDownTimer(10000, 100) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Hey", "onTick: " + String.valueOf(millisUntilFinished) + "has passed");
            }

            @Override
            public void onFinish() {
                Log.i("Hey", "onFinish: counter has down");
            }
        }.start();
    }
}
