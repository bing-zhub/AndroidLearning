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
        return 10;
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
        Glide.with(mContext).load("https://avatars2.githubusercontent.com/u/39323503?s=460&v=4").into(viewHolder.mImageView);
        return convertView;
    }
}
