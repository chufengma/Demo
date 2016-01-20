package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.onfengma.androidprac.views.ScrollDemoLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollerActivity extends AppCompatActivity {

    @Bind(R.id.scroll_layout)
    ScrollDemoLayout scrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        ButterKnife.bind(this);

//        scrollLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                scrollLayout.scrollTo(0, 2000);
//            }
//        }, 4000);
    }




}