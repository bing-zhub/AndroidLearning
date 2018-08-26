package com.example.bing.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {

    private Button mBtn3;
    private TextView mTv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        mBtn3 = (Button)findViewById(R.id.btn_3);
        mTv8 = (TextView)findViewById(R.id.tv8);

        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"Hello World",Toast.LENGTH_LONG).show();
            }
        });

        mTv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"Hello World",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void showToast(View view){
        Toast.makeText(this,"Hello World",Toast.LENGTH_LONG).show();
    }

}
