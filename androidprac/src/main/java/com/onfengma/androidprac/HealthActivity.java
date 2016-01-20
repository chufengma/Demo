package com.onfengma.androidprac;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.onfengma.androidprac.views.HealthView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthActivity extends AppCompatActivity {

    @Bind(R.id.health)
    HealthView health;

    ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);

        animator = ObjectAnimator.ofFloat(health, "angle", 0f, 300f);
        animator.setDuration(4000);
    }

    public void onClick(View view) {
        animator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
