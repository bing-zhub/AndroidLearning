package com.example.bing.fragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContainerActivity extends AppCompatActivity implements FragmentA.IonMessageClick{

    private Fragment mFragA;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mFragA = FragmentA.newInstance("Hello");

        getFragmentManager().beginTransaction().add(R.id.container, mFragA, "a").commitAllowingStateLoss();

        tvTitle = (TextView)findViewById(R.id.target);
    }

//    public void setData(String text){
//        tvTitle.setText(text);
//    }


    @Override
    public void onClick(String text) {
        tvTitle.setText(text);
    }
}
