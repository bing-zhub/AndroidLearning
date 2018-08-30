package com.example.bing.textview.listView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bing.textview.R;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = (ListView)findViewById(R.id.lv_1);
        mListView.setAdapter(new myListAdapter(ListViewActivity.this));
    }
}