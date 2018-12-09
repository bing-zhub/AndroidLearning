package com.example.bing.yiji;

import android.app.Activity;
import android.graphics.Color;

import com.tapadoo.alerter.Alerter;

import java.util.Date;

public class Utilities {
    public static void showNotificationAlerter(Activity activity, String text){
        Alerter.create(activity)
                .setText(text)
                .setDuration(500)
                .setIcon(R.drawable.alerter_ic_notifications)
                .setBackgroundColorInt(Color.parseColor("#EA9453"))
                .show();
    }

    public static String convertDateToString(Date date){
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate() + 1;
        return year+"年"+month+"月"+day+"日";
    }
}
