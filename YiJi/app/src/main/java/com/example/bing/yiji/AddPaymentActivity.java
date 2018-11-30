package com.example.bing.yiji;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;

import java.util.Calendar;
import java.util.Date;

import static com.example.bing.yiji.MainActivity.commonUtils;

public class AddPaymentActivity extends AppCompatActivity implements TypeFragment.FragmentInteraction{

    Toolbar toolbar;
    TabLayout tabLayout;
    FrameLayout typeContainer;
    Payment payment;
    private TextView paymentDate, paymentTime, paymentPrice;
    private EditText paymentDescription, paymentLocation;
    private final static int DATE_DIALOG = 0;
    private final static int TIME_DIALOG = 1;
    private Calendar c = null;
    private Date gDate;

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DATE_DIALOG:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        paymentDate.setText( year + "年" + (month+1) + "月" + dayOfMonth + "日");
                        gDate.setMonth(month);
                        gDate.setYear(year);
                        gDate.setDate(dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;
            case TIME_DIALOG:
                c=Calendar.getInstance();
                dialog=new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener(){
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        paymentTime.setText(hourOfDay+"时"+minute+"分");
                        gDate.setHours(hourOfDay);
                        gDate.setMinutes(minute);
                    }
                },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        false
                );
                break;
        }
        return dialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        gDate = new Date();
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



        paymentDate = findViewById(R.id.payment_date);
        paymentTime = findViewById(R.id.payment_time);
        paymentDescription = findViewById(R.id.payment_description);
        paymentLocation = findViewById(R.id.payment_location);
        paymentPrice = findViewById(R.id.payment_price);
        Date date = new Date(System.currentTimeMillis());

        paymentDate.setText((date.getYear()+1900)+"年"+(date.getMonth()+1)+"月"+ date.getDate()+"日");

        paymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });

        paymentTime.setText(date.getHours()+"时"+date.getMinutes()+"分");
        paymentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG);
            }
        });

        paymentLocation.setText("杭州");
    }

    public void saveData(View view){
        if(payment.getType()==null){
            Toast.makeText(this, "未选择类别", Toast.LENGTH_SHORT).show();
            return;
        }
        if(paymentPrice.getText().toString().isEmpty()){
            Toast.makeText(this, "未设置金额", Toast.LENGTH_SHORT).show();
            return;
        }
        payment.setDate(gDate);
        payment.setNum(Integer.valueOf(paymentPrice.getText().toString()));
        payment.setDescription(paymentDescription.getText().toString());
        payment.setLocation(paymentLocation.getText().toString());
        Toast.makeText(this, payment.getType()+" "+payment.getDescription()+" "+payment.getDate().toString()+" "+payment.getNum(), Toast.LENGTH_SHORT).show();
        commonUtils.insertPayment(payment);
        finish();
    }


    @Override
    public void process(String str) {
        payment.setType(str);
    }
}
