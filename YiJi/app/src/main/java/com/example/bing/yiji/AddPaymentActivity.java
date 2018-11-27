package com.example.bing.yiji;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;

import java.util.Date;

public class AddPaymentActivity extends AppCompatActivity {

    private static CommonUtils commonUtils;
    Toolbar toolbar;
    TabLayout tabLayout;
    FrameLayout typeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        toolbar = findViewById(R.id.add_payment_toolbar);
        Log.d("addPayment", "onCreate: "+AddPaymentActivity.this+" "+commonUtils);
        if(commonUtils==null)
            commonUtils = new CommonUtils(AddPaymentActivity.this);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.close);
        toolbar.setTitle("记一笔");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout = findViewById(R.id.payment_or_income);
        tabLayout.addTab(tabLayout.newTab().setText("支出"));
        tabLayout.addTab(tabLayout.newTab().setText("收入"));

        typeContainer = findViewById(R.id.type_container);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("tab", ""+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void saveData(View view){
        Payment payment = new Payment();
        payment.setDate(new Date(System.currentTimeMillis()));
        payment.setDescription("其他");
        payment.setNum(100);
        payment.setType("衣物");
        commonUtils.insertPayment(payment);
    }


}
