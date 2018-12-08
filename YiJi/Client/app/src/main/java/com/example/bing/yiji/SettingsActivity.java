package com.example.bing.yiji;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private Switch aSwitch1, aSwitch2, aSwitch3, aSwitch4, aSwitch5;
    private Toolbar toolbar;

    SharedPreferences spf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        spf = getSharedPreferences("settings", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        aSwitch1 = findViewById(R.id.switch1);
        aSwitch2 = findViewById(R.id.switch2);
        aSwitch3 = findViewById(R.id.switch3);
        aSwitch4 = findViewById(R.id.switch4);
        aSwitch5 = findViewById(R.id.switch5);

        aSwitch1.setChecked(spf.getBoolean("switch1", false));
        aSwitch2.setChecked(spf.getBoolean("switch2", false));
        aSwitch3.setChecked(spf.getBoolean("switch3", false));
        aSwitch4.setChecked(spf.getBoolean("switch4", false));
        aSwitch5.setChecked(spf.getBoolean("switch5", false));

        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean("switch1", isChecked);
                editor.apply();
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean("switch2", isChecked);
                editor.apply();
            }
        });

        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean("switch3", isChecked);
                editor.apply();
            }
        });

        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean("switch4", isChecked);
                editor.apply();
            }
        });

        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spf.edit();
                editor.putBoolean("switch5", isChecked);
                editor.apply();
            }
        });

        toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("系统偏好设置");
    }
}
