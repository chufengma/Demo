package com.onfengma.androidprac;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class TabLayoutDemoActivity extends AppCompatActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_demo);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        }

        tabLayout.addTab(tabLayout.newTab().setText("疯狂原始人"));
        tabLayout.addTab(tabLayout.newTab().setText("冰雪奇缘"));
        tabLayout.addTab(tabLayout.newTab().setText("精灵旅社"));
        tabLayout.addTab(tabLayout.newTab().setText("冰河世纪"));

        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_view, null);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));

        tabLayout.setTabMode(MODE_SCROLLABLE);
    }


}
