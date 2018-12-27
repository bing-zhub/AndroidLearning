package com.example.bing.yiji.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.bing.yiji.R;

public class HistogramColView extends View {
    int MAX = 100;//矩形显示的最大值
    int corner = 0; //矩形的角度。 设置为0 则没有角度。
    double data = 0.0;//显示的数
    double tempData = 0; //初始数据
    int textPadding = 50; //字体与矩形图的距离
    String label = "";
    Paint mPaint;
    int mColor;
    Context mContext;


    public HistogramColView(Context context) {
        super(context);
        mContext = context;
    }

    public HistogramColView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    public HistogramColView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mColor = Color.GRAY;
        mPaint.setColor(mColor);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (data == 0.0) {
            mPaint.setTextSize(getWidth() / 2);
            RectF oval3 = new RectF(0, getHeight() - pxToDip(mContext, 20), getWidth(), getHeight());// 设置个新的长方形
            canvas.drawRoundRect(oval3, pxToDip(mContext, corner), pxToDip(mContext, corner), mPaint);

            canvas.drawText("0",
                    getWidth() * 0.5f - mPaint.measureText("0") * 0.5f,
                    getHeight() - pxToDip(mContext, 20) - 2 * pxToDip(mContext, textPadding),
                    mPaint);
            return;
        }

        int step = (int) (data / 100 + 1.0);

        if (tempData < data - step) {
            tempData = tempData + step;
        } else {
            tempData = data;
        }
        String S = tempData + ""; //如果数字后面需要加% 则在""中添加%
        if (S.length() < 4) {
            mPaint.setTextSize(getWidth() / 2);
        } else {
            mPaint.setTextSize(50);
        }
        float textH = mPaint.ascent() + mPaint.descent();
        float MaxH = getHeight() - textH - 2 * pxToDip(mContext, textPadding);
        float realH = (float) (MaxH / MAX * tempData);
        RectF oval3 = new RectF(0, getHeight() - realH, getWidth(), getHeight());// 设置个新的长方形
        canvas.drawRoundRect(oval3, pxToDip(mContext, corner), pxToDip(mContext, corner), mPaint);
        canvas.drawText(S,
                getWidth() * 0.5f - mPaint.measureText(S) * 0.5f,
                getHeight() - realH - 2 * pxToDip(mContext, textPadding),
                mPaint);
        canvas.drawText(label,getWidth() * 0.5f - mPaint.measureText(label)*0.5f, 5* pxToDip(mContext, textPadding), mPaint);
        if (tempData != data) {
            postInvalidate();
        }
    }

    public void setData(double data, int MAX, String label) {
        this.data = data;
        this.MAX = MAX+40;
        this.label = label;
        postInvalidate();
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public static int pxToDip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5F);
    }
}