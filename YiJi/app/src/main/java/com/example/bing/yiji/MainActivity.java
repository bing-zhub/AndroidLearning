package com.example.bing.yiji;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    TextView tvHint, tvEmail, tvSignOut;
    public static CommonUtils commonUtils;
    static final int addPayment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commonUtils = new CommonUtils(MainActivity.this);
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
                        mess = "setting";
                        break;
                    case R.id.navi_bakcup:
                        mess = "backup";
                        break;
                    case R.id.navi_restore:
                        mess = "restore";
                        break;
                    case R.id.navi_aboutus:
                        mess = "about us";
                        break;
                }
                Toast.makeText(MainActivity.this, "you click the "+mess+" button", Toast.LENGTH_SHORT).show();
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