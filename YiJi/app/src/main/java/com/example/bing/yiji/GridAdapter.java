package com.example.bing.yiji;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter< GridAdapter.GridViewHolder>{
    private Context context;
    private List<TypeFragment.GridItem> map;
    private OnItemClickListener mItemClickListener;
    private List<Boolean> booleans;

    public GridAdapter(Context context, List<TypeFragment.GridItem> data){
            this.context = context;
            this.map = data;
            booleans = new LinkedList<>();
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GridViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder gridViewHolder, final int i) {

        for (int j = 0; j < map.size(); j++){
            booleans.add(false);
        }

        String name = map.get(i).name;
        gridViewHolder.txt.setText(name);
        int id = map.get(i).id;
        gridViewHolder.img.setImageDrawable(context.getDrawable(id));
        gridViewHolder.itemView.setTag(name);
        gridViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClick((String) v.getTag());
                }
                ImageView view =  v.findViewById(R.id.type_icon);

                if(booleans.get(i)){

                    setImageViewColor(view, R.color.colorWhite);
//                    v.findViewById(R.id.type_icon).setBackgroundColor();
                    booleans.set(i,false);
                } else{
//                    v.findViewById(R.id.type_icon).setBackgroundColor(Color.parseColor("#eff2fa"));
                    setImageViewColor(view, R.color.colorAccent);
                    booleans.set(i,true);
                }

            }
        });
    }

    public static void setImageViewColor(ImageView view, int colorResId) {
        //mutate()
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList = ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }


    @Override
    public int getItemCount() {
        return map.size();
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
