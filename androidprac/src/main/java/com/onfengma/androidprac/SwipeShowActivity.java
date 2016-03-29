package com.onfengma.androidprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.onfengma.androidprac.views.SwipeShowView;

public class SwipeShowActivity extends AppCompatActivity {

    SwipeShowView swipeShowView;
    boolean showed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_show);
        swipeShowView = (SwipeShowView) findViewById(R.id.swipe);
    }

    public void onClick(View view) {
        if (showed) {
            showed = false;
            swipeShowView.hide();
        } else {
            showed = true;
            swipeShowView.show();
        }
    }
}
