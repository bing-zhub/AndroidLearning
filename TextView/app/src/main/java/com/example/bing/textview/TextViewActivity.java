package com.example.bing.textview;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {

    private TextView textView4,textView5,textView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        textView4 = (TextView)findViewById(R.id.tv4);

        textView4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        textView4.getPaint().setAntiAlias(true); //去除锯齿

        textView5 = (TextView)findViewById(R.id.tv5);
        textView5.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        textView6 = (TextView)findViewById(R.id.tv6);
        textView6.setText(Html.fromHtml("<u>Hello World!</u>")); // 下划线
    }
}
