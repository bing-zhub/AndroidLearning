package com.example.bing.yiji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;

import java.util.Date;
import java.util.List;

import static com.example.bing.yiji.StarterActivity.commonUtils;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {

    private Context mContext;
    private List<Payment> list;

    public LinearAdapter(Context context, List<Payment> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LinearAdapter.LinearViewHolder holder, int position) {
        holder.moneyText.setText(list.get(position).getNum()+"");
        holder.titleText.setText(list.get(position).getType());
        String type = list.get(position).getType();
        int drawableId = CommonUtils.payment.get(type);
        holder.iconImage.setImageDrawable(mContext.getDrawable(drawableId));
        Date date = list.get(position).getDate();

        holder.dateText.setText((1900+date.getYear())+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int pos, long id){
        commonUtils.deletePayment(commonUtils.findPayment(id));
        notifyItemRemoved(pos);
        list.remove(pos);
    }

    public void updateItem(List<Payment> list){
        this.list = list;
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
