package com.example.bing.videoandsound;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button btn;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        mediaPlayer = MediaPlayer.create (this, R.raw.demosound);
        btn = findViewById(R.id.btn);
        btn.setText("播放");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    btn.setText("暂停");
                    mediaPlayer.start();
                    flag = false;
                }else{
                    btn.setText("播放");
                    mediaPlayer.pause();
                    flag = true;
                }
            }
        });


    }




}
