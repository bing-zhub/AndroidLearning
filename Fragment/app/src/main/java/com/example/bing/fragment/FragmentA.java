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
        return inflater.inflate(R.layout.fragment_a, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = view.findViewById(R.id.tv1);
        btnChgPara = view.findViewById(R.id.btnChg);
        btnChgToB = view.findViewById(R.id.btnToB);

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
                    getFragmentManager().beginTransaction().replace(R.id.container, fragmentB).addToBackStack(null).commit();
                }
            }
        });
    }


}
