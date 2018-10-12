package com.example.bing.textview.recyclerView;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bing.textview.R;

public class LinearRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        mRvMain = findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));
        mRvMain.addItemDecoration(new MyDecoration());
        mRvMain.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this));
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
