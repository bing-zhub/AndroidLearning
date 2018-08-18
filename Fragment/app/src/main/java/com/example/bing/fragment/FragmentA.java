package com.example.bing.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentA extends Fragment {

    private TextView mTextView;
    private Button btnChgToB;
    private Button btnChgPara;
    private Button btnMess;


    // activity 向 Fragment 传参
    public static FragmentA newInstance(String title){
        FragmentA fragmentA = new FragmentA();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //回退栈的时候 参数会被刷新
        return inflater.inflate(R.layout.fragment_a, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = view.findViewById(R.id.tv1);
        btnChgPara = view.findViewById(R.id.btnChg);
        btnChgToB = view.findViewById(R.id.btnToB);
        btnMess = view.findViewById(R.id.mess);

        if(getArguments()!=null){
            mTextView.setText(getArguments().getString("title"));
        }

        btnChgPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("World");
            }
        });

        btnChgToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view != null){
                    FragmentB fragmentB = new FragmentB();
                    Fragment fragment = getFragmentManager().findFragmentByTag("a");
                    // 先hide 在add 可以避免view被重新绘制. 保存参数
                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.container, fragmentB).addToBackStack(null).commit();
                    // replace 会导致view重新绘制 丢失参数
//                    getFragmentManager().beginTransaction().replace(R.id.container, fragmentB).addToBackStack(null).commit();
                }
            }
        });

        btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ContainerActivity)getActivity()).setData("Hello");
            }
        });
    }


}
