package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChangeTopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_top);
        final View view = findViewById(R.id.layout);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.offsetTopAndBottom(460);
            }
        }, 3000);
    }
}
