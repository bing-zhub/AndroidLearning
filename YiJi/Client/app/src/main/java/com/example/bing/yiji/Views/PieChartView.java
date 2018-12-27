package com.example.bing.yiji.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.bing.yiji.Model.PieData;

import java.util.List;

public class PieChartView extends View {
    private Paint mPaint;
    // 饼状图初始绘制角度
    List<PieData> data;
    float mStartAngle = 0;
    RectF rectF;

    public PieChartView(Context context) {
        super(context);
    }
    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void setData(List<PieData> dataList){
        this.data = dataList;
        postInvalidate();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(4);
        rectF = new RectF(0, 0, 600, 600); //正方形背景
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(data == null || data.size() == 0)
            return;

        float currentAngle = mStartAngle;
        for (int i = 0;i<data.size();i++){
            PieData pieData = data.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF,currentAngle,pieData.getAngle(),true,mPaint); //画弧
            currentAngle += pieData.getAngle();
        }
    }
}