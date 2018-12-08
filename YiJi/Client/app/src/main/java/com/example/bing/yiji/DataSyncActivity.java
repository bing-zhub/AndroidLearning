package com.example.bing.yiji;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.bing.yiji.Adapter.CloudRecordAdapter;
import com.example.bing.yiji.Model.RecordItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.payment.entity.Payment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_BOTTOM;
import static com.example.bing.yiji.StarterActivity.commonUtils;

public class DataSyncActivity extends AppCompatActivity {

    RecyclerView rvDataRecordList;
    private List<RecordItem> data;
    private TextView localDataRecordSum,localDataTotal,localDataIncome,localDataOutcome;
    private int localRecordTotal, localInome, localOutcome, localTotal = 0;
    boolean isExist = false;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        toolbar = findViewById(R.id.backupData_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("数据备份与恢复");

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

        CloudRecordAdapter adapter = new CloudRecordAdapter(R.layout.cloud_data_item, data);
        adapter.openLoadAnimation(SLIDEIN_BOTTOM);
        adapter.setEmptyView(R.layout.emty_view, (ViewGroup) rvDataRecordList.getParent());
        rvDataRecordList.setAdapter(adapter);
        rvDataRecordList.addItemDecoration(new SpacesItemDecoration(50));
    }

    public void getData() {
       ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserBackup");
        try {
            List<ParseObject> objects = query.find();
            for (ParseObject parseObject: objects) {
                RecordItem item = new RecordItem();
                item.setDate(new Date(parseObject.getLong("date")));
                item.setOutcome(parseObject.getInt("outcome"));
                item.setIncome(parseObject.getInt("income"));
                item.setSum(parseObject.getInt("recordSum"));
                List<Payment> payments = new LinkedList<>();
                JSONArray array = parseObject.getJSONArray("content");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject record = (JSONObject) array.get(0);
                    Payment payment = new Payment();
                    payment.setLocation(record.getString("location"));
                    payment.setDescription(record.getString("description"));
                    payment.setNum(record.getInt("num"));
                    payment.setType(record.getString("type"));
                    payment.setDate(new Date(record.getLong("date")));
                    payments.add(payment);
                }
                item.setContents(payments);
                data.add(item);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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
        List<Map<String, String>> content = new LinkedList<>();
        for (Payment p: commonUtils.listAllPayments()){
            Map<String, String> record = new HashMap<>();
            record.put("type", p.getType());
            record.put("num", p.getNum()+"");
            record.put("date", p.getDate().getTime()+"");
            record.put("description", p.getDescription());
            record.put("location", p.getLocation());
            content.add(record);
        }
        long contentHash = content.toString().hashCode();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserBackup");
        query.whereEqualTo("contentHash", contentHash);
        try {
            List<ParseObject> objects =  query.find();
            ParseObject object;
            if(objects.size()!=0){
                object = objects.get(0);
                long time = object.getLong("date");
                Date date = new Date(time);
                Utilities.showNotificationAlerter(DataSyncActivity.this, "已经存在相同备份, 见"+Utilities.convertDateToString(date)+"备份");
                isExist = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!isExist){
            ParseObject object = new ParseObject("UserBackup");
            object.put("recordSum", localRecordTotal);
            object.put("income", localInome);
            object.put("outcome",localOutcome);
            object.put("date",System.currentTimeMillis());
            object.put("contentHash", content.toString().hashCode());
            object.put("content", content);
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Utilities.showNotificationAlerter(DataSyncActivity.this, "备份成功");
                        data.clear();
                        getData();
                        rvDataRecordList.getAdapter().notifyDataSetChanged();
                    }else{
                        Log.d("backup", e.getMessage());
                    }
                }
            });
        }
    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }

}
