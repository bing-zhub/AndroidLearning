package com.example.bing.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {

    private CheckBox mChecxBox5, mCheckBox6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        mCheckBox6 = (CheckBox)findViewById(R.id.cb_5);
        mChecxBox5 = (CheckBox)findViewById(R.id.cb_6);

        mChecxBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CheckBoxActivity.this, isChecked?"5选中":"5未选中 ",Toast.LENGTH_SHORT).show();
            }
        });

        mCheckBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CheckBoxActivity.this, isChecked?"6选中":"6未选中 ",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
