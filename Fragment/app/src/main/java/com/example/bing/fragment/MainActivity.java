package com.example.bing.fragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private Fragment mFragA;
    private Fragment mFragB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = findViewById(R.id.change);
        mFragA = FragmentA.newInstance("Hello");
        mFragB = new FragmentB();

        getFragmentManager().beginTransaction().add(R.id.container, mFragA).commitAllowingStateLoss();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(view.getId());
                getFragmentManager().beginTransaction().replace(R.id.container, mFragB).commitAllowingStateLoss();
            }
        });

    }

}
