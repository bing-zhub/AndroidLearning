package com.example.bing.somestuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLottie, btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLottie = findViewById(R.id.btn_lottie);
        btnDownload = findViewById(R.id.btn_download);

        btnLottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LottieActivity.class);
                startActivity(intent);
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void main(String[] args) {
        String text = "head body foot";
        Pattern p = Pattern.compile("head(.*?)foot");
        Matcher m = p.matcher(text);
        while(m.find())
            System.out.println(m.group(1));
    }


}
