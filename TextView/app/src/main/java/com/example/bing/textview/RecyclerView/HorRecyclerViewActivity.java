package com.example.bing.textview.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.bing.textview.R;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class HorRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvHor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_recycker_view);
        mRvHor = findViewById(R.id.rv_hor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HorRecyclerViewActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvHor.setLayoutManager(linearLayoutManager);
        mRvHor.setAdapter(new HorAdapter(HorRecyclerViewActivity.this));
    }
}
