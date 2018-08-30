package com.example.bing.textview.gridView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.bing.textview.R;

public class GridViewActivity extends AppCompatActivity {

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mGridView = (GridView)findViewById(R.id.gd_1);
        mGridView.setAdapter(new myGridViewAdapter(GridViewActivity.this));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this,"click pos:"+position, Toast.LENGTH_SHORT).show();
            }
        });

        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this, "long press pos:"+position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
