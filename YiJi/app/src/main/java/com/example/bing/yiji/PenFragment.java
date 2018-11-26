package com.example.bing.yiji;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bing.yiji.Model.Transaction;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class PenFragment extends Fragment {

    private SwipeMenuRecyclerView mRvIncome, mRvOutcome;

    public PenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pen, container, false);
        mRvIncome = view.findViewById(R.id.rv_income);
        setConfigToIncome();

        mRvOutcome = view.findViewById(R.id.rv_outcome);
        setConfigToOutCome();

        return view;
    }

    private void insertFakeData(){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("transaction", MODE_PRIVATE, null);
    }

    private List<Transaction> getData(boolean income){
        List<Transaction> list = new ArrayList<>();
        return list;
    }

    private void setConfigToOutCome() {
        mRvOutcome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvOutcome.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                        .setText("删除")
                        .setBackground(R.color.colorAccent)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)//设置高，这里使用match_parent，就是与item的高相同
                        .setWidth(300);//设置宽
                swipeRightMenu.addMenuItem(deleteItem);//设置右边的侧滑
            }
        });

        mRvOutcome.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                Toast.makeText(getContext(), "删除"+adapterPosition, Toast.LENGTH_SHORT).show();
            }
        });
        mRvOutcome.setAdapter(new LinearAdapter(getActivity(), "支出"));
    }

    private void setConfigToIncome() {
        mRvIncome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvIncome.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                        .setBackground(R.color.colorAccent)
                        .setText("删除")
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                        .setWidth(300);
                swipeRightMenu.addMenuItem(deleteItem);//设置右边的侧滑
            }
        });

        mRvIncome.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                Toast.makeText(getContext(), "删除"+adapterPosition, Toast.LENGTH_SHORT).show();
            }
        });
        mRvIncome.setAdapter(new LinearAdapter(getActivity(), "收入"));
        mRvIncome.setItemAnimator(new DefaultItemAnimator());
    }

}
