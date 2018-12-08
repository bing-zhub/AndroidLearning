package com.example.bing.yiji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bing.yiji.Fragment.ChartFragment;
import com.example.bing.yiji.Fragment.PenFragment;
import com.facebook.stetho.Stetho;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    TextView tvHint, tvEmail, tvSignOut;
    static final int addPayment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        toolbar = findViewById(R.id.tool_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation);
        frameLayout = findViewById(R.id.fragment_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.inflateMenu(R.menu.action_bar);

        PenFragment penFragment = new PenFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, penFragment).commit();
        toolbar.setTitle("记账");

        //点击左边返回按钮监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String mess = "";
                switch(item.getItemId()){
                    case R.id.add:
                        Intent intent = new Intent(MainActivity.this, AddPaymentActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        mess = "search";
                        Toast.makeText(MainActivity.this, "you click the "+mess+" button", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.pen:
                        PenFragment penFragment = new PenFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, penFragment).commit();
                        toolbar.setTitle("记账");
                        break;
                    case R.id.chart:
                        ChartFragment chartFragment = new ChartFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, chartFragment).commit();
                        toolbar.setTitle("统计");
                        break;
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String mess = "";
                switch (menuItem.getItemId()){
                    case R.id.navi_setting:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                    case R.id.navi_bakcup:
                        startActivity(new Intent(MainActivity.this, DataSyncActivity.class));
                        break;
                    case R.id.navi_restore:
                        mess = "restore";
                        break;
                    case R.id.navi_aboutus:
                        mess = "about us";
                        break;
                }
                return false;
            }
        });

        View header =  navigationView.getHeaderView(0);
        ImageView btnLogin = header.findViewById(R.id.btn_login);
        tvSignOut = header.findViewById(R.id.btn_sign_out);
        tvEmail = header.findViewById(R.id.tv_email);
        tvHint = header.findViewById(R.id.hint_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        showGuide();
    }

    public void showGuide(){
        SharedPreferences spf = getSharedPreferences("data", MODE_PRIVATE);
        boolean isFirstStartUp =  spf.getBoolean("MainFirstStartUp", true);

        if(!isFirstStartUp) return;

        spf.edit().putBoolean("MainFirstStartUp", false).apply();
        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(bottomNavigationView.findViewById(R.id.chart), "统计信息", "点击这里以查看统计信息")
                                .textColor(R.color.colorAccent)
                                .outerCircleColor(R.color.colorWhite)
                                .drawShadow(true)
                                .targetCircleColor(R.color.colorPrimary)
                                .textTypeface(Typeface.SANS_SERIF),
                        TapTarget.forView(bottomNavigationView.findViewById(R.id.pen), "资金往来记录", "点击这里以查看最近资金往来")
                                .textColor(R.color.colorAccent)
                                .outerCircleColor(R.color.colorWhite)
                                .drawShadow(true)
                                .targetCircleColor(R.color.colorPrimary),
                        TapTarget.forView(toolbar.findViewById(R.id.add),"添加记录", "点击这里以添加记录")
                                .textColor(R.color.colorAccent)
                                .outerCircleColor(R.color.colorWhite)
                                .drawShadow(true)
                                .targetCircleColor(R.color.colorPrimary)
                ).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                toolbar.findViewById(R.id.add).callOnClick();
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

            }

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {
                // Boo
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == addPayment){
            if(resultCode == RESULT_OK){
                String message = (String) data.getExtras().get("data");
                Log.d("result", "onActivityResult: "+message);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}