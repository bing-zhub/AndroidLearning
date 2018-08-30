package com.example.bing.textview.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bing.textview.R;

public class myListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public myListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 11;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView iv;
        public TextView tv_title, tv_time, tv_content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){

            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.image_view);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //给控件赋值
        holder.tv_title.setText("this is title");
        holder.tv_content.setText("this is content");
        holder.tv_time.setText("this is time");
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535635147593&di=161cf95bdef70a3172081defba2f9435&imgtype=0&src=http%3A%2F%2Fis2.mzstatic.com%2Fimage%2Fthumb%2FPurple1%2Fv4%2Feb%2F5c%2F45%2Feb5c4505-41a3-67c6-3785-c14ad4083f5e%2Fmzl.kwwfpsun.png%2F0x0ss-30.jpg").into(holder.iv);

        return convertView;
    }
}