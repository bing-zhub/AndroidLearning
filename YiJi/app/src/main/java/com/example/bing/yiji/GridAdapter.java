package com.example.bing.yiji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter< GridAdapter.GridViewHolder> implements View.OnClickListener{
    private Context context;
    private List<TypeFragment.GridItem> map;
    private OnItemClickListener mItemClickListener;

    public GridAdapter(Context context, List<TypeFragment.GridItem> data){
            this.context = context;
            this.map = data;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GridViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder gridViewHolder, int i) {
        String name = map.get(i).name;
        gridViewHolder.txt.setText(name);
        int id = map.get(i).id;
        gridViewHolder.img.setImageDrawable(context.getDrawable(id));
        gridViewHolder.itemView.setTag(name);
        gridViewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((String) v.getTag());
        }
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    class GridViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txt;

        private GridViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.type_icon);
            txt = itemView.findViewById(R.id.type_txt);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String position);
    }

}
