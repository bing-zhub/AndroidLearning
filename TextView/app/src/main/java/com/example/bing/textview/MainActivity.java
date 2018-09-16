package com.example.bing.textview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bing.textview.gridView.GridViewActivity;
import com.example.bing.textview.listView.ListViewActivity;

public class MainActivity extends AppCompatActivity {


    private Button mBtnTextView;
    private Button mBtnButton;
    private Button mBtnEditText;
    private Button mBtnRadioButton;
    private Button mBtnCheckBox;
    private Button mBtnImageView;
    private Button mBtnListView;
    private Button mBtnGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnTextView = (Button)findViewById(R.id.btn_textView);
        mBtnButton = (Button)findViewById(R.id.btn_button);
        mBtnEditText = (Button)findViewById(R.id.btn_editText);
        mBtnRadioButton = (Button)findViewById(R.id.btn_radioButton);
        mBtnCheckBox = (Button)findViewById(R.id.btn_checkBox);
        mBtnImageView = (Button)findViewById(R.id.btn_imageView);
        mBtnListView = (Button)findViewById(R.id.btn_listView);
        mBtnGridView = (Button)findViewById(R.id.btn_gridView);

       setListener();
    }

    private void setListener(){
        OnClick onClick = new OnClick();
        mBtnRadioButton.setOnClickListener(onClick);
        mBtnEditText.setOnClickListener(onClick);
        mBtnButton.setOnClickListener(onClick);
        mBtnTextView.setOnClickListener(onClick);
        mBtnCheckBox.setOnClickListener(onClick);
        mBtnImageView.setOnClickListener(onClick);
        mBtnListView.setOnClickListener(onClick);
        mBtnGridView.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v){
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_textView:
                    intent = new Intent(MainActivity.this, TextViewActivity.class);
                    break;

                case R.id.btn_button:intent = new Intent(MainActivity.this, ButtonActivity.class);
                    break;

                case R.id.btn_editText:
                    intent = new Intent(MainActivity.this, EditTextActivity.class);
                    break;

                case R.id.btn_radioButton:
                    intent = new Intent(MainActivity.this, RadioButtonActivity.class);
                    break;
                case R.id.btn_checkBox:
                    intent = new Intent(MainActivity.this, CheckBoxActivity.class);
                    break;
                case R.id.btn_imageView:
                    intent = new Intent(MainActivity.this, ImageViewActivity.class);
                case R.id.btn_listView:
                    intent = new Intent(MainActivity.this, ListViewActivity.class);
                    break;
                case R.id.btn_gridView:
                    intent = new Intent(MainActivity.this, GridViewActivity.class);
                    break;
            }
            startActivity(intent);
        }

    }

}
