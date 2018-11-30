package com.example.bing.yiji;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bing.yiji.Model.Transaction;
import com.example.bing.yiji.dbmanager.CommonUtils;
import com.payment.entity.Payment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bing.yiji.MainActivity.commonUtils;


public class PenFragment extends Fragment {

    private SwipeMenuRecyclerView mRvIncome, mRvOutcome;
    private List<Payment> paymentList;
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

    @Override
    public void onAttach(Context context) {
        getData();
        super.onAttach(context);
    }

    private void getData(){
        paymentList = commonUtils.listAllPayments();
    }

    private void setConfigToOutCome() {
        getData();
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

        final LinearAdapter linearAdapter = new LinearAdapter(getActivity(), paymentList);
        mRvOutcome.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                commonUtils.deltePayment(commonUtils.findPayment(paymentList.get(adapterPosition).getId()));
                linearAdapter.notifyItemRemoved(adapterPosition);
                getData();
                Toast.makeText(getContext(), "删除"+paymentList.get(adapterPosition).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        mRvOutcome.setAdapter(linearAdapter);
    }

    private void setConfigToIncome() {
        getData();
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
        mRvIncome.setAdapter(new LinearAdapter(getActivity(), paymentList));
        mRvIncome.setItemAnimator(new DefaultItemAnimator());
    }



}
