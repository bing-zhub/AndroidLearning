package com.example.bing.yiji;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TypeFragment extends Fragment implements GridAdapter.OnItemClickListener{

    private RecyclerView typeList;
    private String type;
    private static List<GridItem> payment, income;
    private FragmentInteraction listener;

    public TypeFragment(){
        payment = new LinkedList<>();
        income = new LinkedList<>();
        setupMap();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        type = getArguments().getString("type");
        View view = inflater.inflate(R.layout.fragment_type, container,false);
        typeList = view.findViewById(R.id.type_list);
        typeList.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        GridAdapter gridAdapter = new GridAdapter(getActivity(), type.equals("支出")?payment:income);
        gridAdapter.setItemClickListener(this);
        typeList.setAdapter(gridAdapter);
        return view;
    }

    @Override
    public void onItemClick(String position) {
        listener.process(position);
    }

    public class GridItem implements Checkable {
        String name;
        int id;
        private boolean mChecked;

        public GridItem(){

        }
        private GridItem(String name, int id){
            this.name = name;
            this.id = id;
        }

        @Override
        public void setChecked(boolean checked) {
            mChecked = checked;
        }

        @Override
        public boolean isChecked() {
            return mChecked;
        }

        @Override
        public void toggle() {
            setChecked(!mChecked);
        }
    }

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void process(String str);
    }

    // 当FRagmen被加载到activity的时候会被回调
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FragmentInteraction) {
            listener = (FragmentInteraction)activity; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    private void setupMap(){
        GridItem food = new GridItem("餐饮食品", R.drawable.food);
        GridItem cloth =new GridItem("衣服饰品", R.drawable.clothe);
        GridItem home =new GridItem("居家生活", R.drawable.home);
        GridItem bus =new GridItem("行车交通", R.drawable.bus);
        GridItem joystick =new GridItem("休闲娱乐", R.drawable.joystick);
        GridItem  book =new GridItem("文化教育", R.drawable.book);
        GridItem health =new GridItem("健康医疗", R.drawable.health);
        GridItem money =new GridItem("投资支出", R.drawable.money);
        GridItem wallet =new GridItem("其他支出", R.drawable.wallet);
        GridItem settings =new GridItem("设置", R.drawable.settings);
        payment.add(food);payment.add(cloth);payment.add(home);payment.add(bus); payment.add(joystick);
        payment.add(book); payment.add(health); payment.add(money);payment.add(wallet); payment.add(settings);
        GridItem empty =new GridItem("空",R.drawable.add);
        income.add(empty);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
