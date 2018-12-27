package com.example.bing.yiji.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.example.bing.yiji.Model.PieData;
import com.example.bing.yiji.R;
import com.example.bing.yiji.Views.HistogramColView;
import com.example.bing.yiji.Views.PieChartView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChartFragment extends Fragment {

    HistogramColView histogramColView1;
    HistogramColView histogramColView2;
    HistogramColView histogramColView3;
    HistogramColView histogramColView4;
    HistogramColView histogramColView5;
    PieChartView pieChartView;


    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        histogramColView1 = view.findViewById(R.id.histogramCol1);
        histogramColView2 = view.findViewById(R.id.histogramCol2);
        histogramColView3 = view.findViewById(R.id.histogramCol3);
        histogramColView4 = view.findViewById(R.id.histogramCol4);
        histogramColView5 = view.findViewById(R.id.histogramCol5);
        histogramColView1.setData(100,150, "label1");
        histogramColView2.setData(150,150, "label2");
        histogramColView3.setData(120,150, "label3");
        histogramColView4.setData(90,150, "label4");
        histogramColView5.setData(80,150, "label5");


        int values[] = {30, 30, 30}; //右侧的图的数据，{10, 20, 70} 图左
        List<PieData> pieDataList = new LinkedList<>();
        PieData pieData1 = new PieData();
        int sum = sum(values);
        pieData1.setPercentage((float) values[0] / sum);
        pieData1.setAngle(pieData1.getPercentage() * 360) ;
        pieData1.setColor(Color.RED);
        pieDataList.add(pieData1);

        PieData pieData2 = new PieData();
        pieData2.setPercentage((float) values[1] / sum);
        pieData2.setAngle(pieData2.getPercentage() * 360) ;
        pieData2.setColor(Color.GREEN);
        pieDataList.add(pieData2);

        PieData pieData3 = new PieData();
        pieData3.setPercentage((float) values[2] / sum);
        pieData3.setAngle(pieData3.getPercentage() * 360) ;
        pieData3.setColor(Color.YELLOW);
        pieDataList.add(pieData3);

        pieChartView = view.findViewById(R.id.pieChartView);

        for (PieData d: pieDataList){
            System.out.println(d.getAngle() + " "+d.getPercentage());
        }
        pieChartView.setData(pieDataList);
        return view;
    }

    private int sum(int values[]){
        int sum = 0;
        for (int i : values){
            sum += i;
        }
        return sum;
    }
}
