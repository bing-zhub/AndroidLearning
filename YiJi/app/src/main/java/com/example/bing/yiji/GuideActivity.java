package com.example.bing.yiji;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class GuideActivity extends AppCompatActivity {

    private android.support.v4.view.ViewPager vpContent;
//    private Button btnSkip;
    private int[] datas;

    private void bindViews() {
        vpContent = (android.support.v4.view.ViewPager) findViewById(R.id.vpContent);
//        btnSkip = (Button) findViewById(R.id.btnSkip);
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                skip();
//            }
//        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        bindViews();
        datas = new int[] { R.drawable.launch_1, R.drawable.launch_2,
                R.drawable.launch_3, R.drawable.launch_4 };
        vpContent.setAdapter(new MyPagerAdapter());
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                if (position == 3) {
                    skip();
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    public void skip() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int i = datas[position];
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(i);
            container.addView(imageView);
            return imageView;
        }

    }
}