package com.onfengma.androidprac;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeterialAnimActivity extends AppCompatActivity {

    @Bind(R.id.view_1)
    View view1;
    @Bind(R.id.view_2)
    Button view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meterial_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.view_2)
    public void onClick() {
        int cx = (view1.getLeft() + view1.getRight()) / 2;
        int cy = (view1.getTop() + view1.getBottom()) / 2;

        int finalRadius = Math.max(view1.getWidth(), view1.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(view1, view1.getLeft(), view1.getTop(), 0, finalRadius);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view1.setVisibility(View.VISIBLE);
            }
        });
        anim.start();
    }

    @OnClick(R.id.view_3)
    public void onClickDisappear() {
        int cx = (view1.getLeft() + view1.getRight()) / 2;
        int cy = (view1.getTop() + view1.getBottom()) / 2;

        int finalRadius = Math.max(view1.getWidth(), view1.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(view1, view1.getLeft(), view1.getTop(), finalRadius, 0);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view1.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

    @OnClick(R.id.view_4)
    public void onClickOpenRecyclerView() {
        RecyclerViewActivity.start(this);
    }

}
