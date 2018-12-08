package com.example.bing.yiji.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bing.yiji.Model.RecordItem;
import com.example.bing.yiji.R;
import com.example.bing.yiji.Utilities;

import java.util.List;

public class CloudRecordAdapter extends BaseQuickAdapter<RecordItem, BaseViewHolder> {


    public CloudRecordAdapter(int layoutResId, @Nullable List<RecordItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordItem item) {
        helper.setText(R.id.itemDate, "备份日期: "+ Utilities.convertDateToString(item.getDate()));
        helper.setText(R.id.itemRecordSum, "记录数: "+item.getSum()+"");
        helper.setText(R.id.itemIncome, "总收入: "+item.getIncome()+"");
        helper.setText(R.id.itemOutcome, "总支出: "+item.getOutcome()+"");
    }
}

