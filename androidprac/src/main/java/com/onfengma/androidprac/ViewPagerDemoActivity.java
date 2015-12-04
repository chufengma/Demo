package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.android.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerDemoActivity extends AppCompatActivity {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        ButterKnife.bind(this);

        viewPager.setAdapter(new DemoPagerAdapter());
        viewPager.setPageMargin(- 240);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setPageTransformer(false, new DemoTrans());
    }

    class DemoPagerAdapter extends PagerAdapter {

        int count = 5;

        public DemoPagerAdapter() {
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = LayoutInflater.from(ViewPagerDemoActivity.this).inflate(R.layout.view_pager_demo, null);
            container.addView(v);
            v.requestLayout();
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    class DemoTrans implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            float pos = Math.abs(position) * 0.2f;
            page.setScaleY(1 - pos);
            page.setScaleX(1 - pos);
        }

    }
}
