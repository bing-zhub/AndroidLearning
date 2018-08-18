package com.example.bing.fragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContainerActivity extends AppCompatActivity {

    private Fragment mFragA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mFragA = FragmentA.newInstance("Hello");

        getFragmentManager().beginTransaction().add(R.id.container, mFragA, "a").commitAllowingStateLoss();


    }
}
