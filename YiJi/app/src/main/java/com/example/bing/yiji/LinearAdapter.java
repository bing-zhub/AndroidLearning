package com.example.bing.yiji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {

    private Context mContext;
    private String flag;

    public LinearAdapter(Context context, String flag){
        this.mContext = context;
        this.flag = flag;
    }

    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LinearAdapter.LinearViewHolder holder, int position) {
        if(flag.equals("支出")){
            holder.titleText.setText("支出");
            holder.moneyText.setText("-100");
        } else{
            holder.titleText.setText("收入");
            holder.moneyText.setText("+100");
        }

        holder.iconImage.setImageResource(R.drawable.money);
        holder.dateText.setText("2018-12-1");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView iconImage;
        private TextView dateText;
        private TextView moneyText;
        private TextView titleText;

        public LinearViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.rv_item_icon);
            dateText = itemView.findViewById(R.id.rv_item_date);
            moneyText = itemView.findViewById(R.id.rv_item_money);
            titleText = itemView.findViewById(R.id.rv_item_title);
        }
    }
}
