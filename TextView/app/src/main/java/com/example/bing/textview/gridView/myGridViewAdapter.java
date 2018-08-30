package com.example.bing.textview.gridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bing.textview.R;

public class myGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public  myGridViewAdapter(Context context){
        this.mContext = context;
        mLayoutInflater  = LayoutInflater.from(mContext);
    }

    public static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }

    @Override
    public int getCount() {
        return 24;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = convertView.findViewById(R.id.iv_gv);
            viewHolder.mTextView = convertView.findViewById(R.id.tv_title);
                    convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView.setText("花");
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535635147593&di=161cf95bdef70a3172081defba2f9435&imgtype=0&src=http%3A%2F%2Fis2.mzstatic.com%2Fimage%2Fthumb%2FPurple1%2Fv4%2Feb%2F5c%2F45%2Feb5c4505-41a3-67c6-3785-c14ad4083f5e%2Fmzl.kwwfpsun.png%2F0x0ss-30.jpg").into(viewHolder.mImageView);
        return convertView;
    }
}
