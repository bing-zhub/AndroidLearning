package com.example.bing.yiji;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;

import java.util.Date;

public class AddPaymentActivity extends AppCompatActivity implements TypeFragment.FragmentInteraction{

    private static CommonUtils commonUtils;
    Toolbar toolbar;
    TabLayout tabLayout;
    FrameLayout typeContainer;
    Payment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        payment = new Payment();
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
        Bundle bundle = new Bundle();
        bundle.putString("type","支出");
        TypeFragment typeFragment = new TypeFragment();
        typeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.type_container, typeFragment).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle bundle = new Bundle();
                bundle.putString("type",tab.getPosition()==0?"支出":"收入");
                TypeFragment typeFragment = new TypeFragment();
                typeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.type_container, typeFragment).commit();
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
        Toast.makeText(this, payment.getType(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void process(String str) {
        payment.setType(str);
    }
}
