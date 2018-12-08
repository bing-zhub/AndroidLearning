package com.example.bing.yiji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bing.yiji.Adapter.CloudRecordAdapter;
import com.example.bing.yiji.Model.RecordItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.payment.entity.Payment;

import org.json.JSONException;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static com.example.bing.yiji.StarterActivity.commonUtils;

public class DataSyncActivity extends AppCompatActivity {

    RecyclerView rvDataRecordList;
    private List<RecordItem> data;
    private TextView localDataRecordSum,localDataTotal,localDataIncome,localDataOutcome;
    private int localRecordTotal, localInome, localOutcome, localTotal = 0;
    boolean isExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        localDataRecordSum = findViewById(R.id.localDataRecordSum);
        localDataTotal = findViewById(R.id.localDataTotal);
        localDataIncome = findViewById(R.id.localDataIncome);
        localDataOutcome = findViewById(R.id.localDataOutcome);
        getLocalData();
        setLocalDataToView();

        rvDataRecordList = findViewById(R.id.dataRecordList);
        data = new LinkedList<>();
        getData();
        rvDataRecordList.setLayoutManager(new LinearLayoutManager(this));
        rvDataRecordList.setAdapter(new CloudRecordAdapter(R.layout.cloud_data_item, data));
    }

    public void getData() {
        for (int i = 0; i < 5; i++){
            RecordItem item = new RecordItem();
            item.setDate(new Date(System.currentTimeMillis()));
            item.setIncome(new Random().nextInt());
            item.setOutcome(new Random().nextInt());
            item.setSum(new Random().nextInt());
            data.add(item);
        }
    }

    public void getLocalData(){
        List<Payment> list = commonUtils.listAllPayments();
        for (Payment p : list){
            localRecordTotal ++;
            localOutcome += p.getNum();
            localTotal += p.getNum();
        }
    }

    public void setLocalDataToView(){
        localDataRecordSum.setText("记录数: "+localRecordTotal);
        localDataTotal.setText("总金额: "+localTotal);
        localDataIncome.setText("总收入: "+localInome);
        localDataOutcome.setText("总支出: "+localOutcome);
    }


    public void restoreData(View view){

    }

    public void backupData(View view){
        Map<String, String> content = new HashMap<>();
        for (Payment p: commonUtils.listAllPayments()){
            content.put("type", p.getType());
            content.put("num", p.getNum()+"");
            content.put("date", p.getDate().getTime()+"");
            content.put("description", p.getDescription());
            content.put("location", p.getLocation());
        }
        long contentHash = content.toString().hashCode();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserBackup");
        query.whereEqualTo("contentHash", contentHash);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size()!=0){
                    ParseObject object = objects.get(0);
                    long time = 0;
                    try {
                        time = Long.parseLong(object.getJSONArray("date").get(0).toString());
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    Date date = new Date(time);
                    Utilities.showNotificationAlerter(DataSyncActivity.this, "已经存在相同备份, 见"+Utilities.convertDateToString(date)+"备份");
                    isExist = true;
                }
            }
        });

        if(!isExist){
            ParseObject object = new ParseObject("UserBackup");
            object.add("recordSum", localRecordTotal);
            object.add("income", localInome);
            object.add("outcome",localOutcome);
            object.add("date",System.currentTimeMillis());
            object.add("contentHash", content.toString().hashCode());
            object.add("content", content);
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Utilities.showNotificationAlerter(DataSyncActivity.this, "备份成功");
                    }else{
                        Log.d("backup", e.getMessage());
                    }
                }
            });
        }
    }
}
