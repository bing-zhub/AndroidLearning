package com.example.bing.yiji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class GridAdapter extends RecyclerView.Adapter< GridAdapter.GridViewHolder> {
    private Context context;
    private Map<String, Integer> map;

    public GridAdapter(Context context, Map<String, Integer> data){
            this.context = context;
            this.map = data;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GridViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txt;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.type_icon);
            txt = itemView.findViewById(R.id.type_txt);
        }
    }
}
