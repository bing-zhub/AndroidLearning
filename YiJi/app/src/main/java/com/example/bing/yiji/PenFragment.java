package com.example.bing.yiji;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.payment.entity.Payment;
import com.tapadoo.alerter.Alerter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.LinkedList;
import java.util.List;

import static com.example.bing.yiji.StarterActivity.commonUtils;

public class PenFragment extends Fragment {

    private SwipeMenuRecyclerView mRvIncome, mRvOutcome;
    private List<Payment> paymentList;
    private TextView paymentSum;
    LinearAdapter linearAdapter;
    public PenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        getData();
        linearAdapter.updateItem(paymentList);
        linearAdapter.notifyDataSetChanged();
        updateSum();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pen, container, false);
        paymentSum = view.findViewById(R.id.outcome_sum);
        mRvIncome = view.findViewById(R.id.rv_income);
        setConfigToIncome();

        mRvOutcome = view.findViewById(R.id.rv_outcome);
        setConfigToOutCome();

        return view;
    }

    private void getData(){
        paymentList = commonUtils.listAllPayments();
    }

    private void updateSum(){
        int sum = 0;
        getData();
        for (Payment p: paymentList){
            sum += p.getNum();
        }
        paymentSum.setText(sum+"");
    }

    private void setConfigToOutCome() {
        updateSum();
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

        linearAdapter = new LinearAdapter(getActivity(), paymentList);

        mRvOutcome.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                removeItem(adapterPosition);
            }
        });
        mRvOutcome.setAdapter(linearAdapter);
    }

    private void removeItem(int adapterPosition){
        Payment payment = paymentList.get(adapterPosition);
        Alerter.create(getActivity())
                .setBackgroundColorInt(Color.parseColor("#EA9453"))
                .setIcon(R.drawable.alerter_ic_notifications)
                .setText(payment.getType()+"类"+payment.getNum()+"元消费已删除")
                .show();
//        Toast.makeText(getContext(), "删除"+paymentList.get(adapterPosition).getId(), Toast.LENGTH_SHORT).show();
        linearAdapter.removeItem(adapterPosition, paymentList.get(adapterPosition).getId());
        updateSum();
    }

    private void setConfigToIncome() {
        List<Payment> list = new LinkedList<>();
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
        mRvIncome.setAdapter(new LinearAdapter(getActivity(), list));
        mRvIncome.setItemAnimator(new DefaultItemAnimator());
    }
}
