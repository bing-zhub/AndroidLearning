package com.example.bing.yiji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;

import java.util.Date;

public class AddPaymentActivity extends AppCompatActivity {

    private CommonUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        commonUtils = new CommonUtils(this);
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
