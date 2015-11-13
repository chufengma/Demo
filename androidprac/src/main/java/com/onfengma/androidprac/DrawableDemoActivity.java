package com.onfengma.androidprac;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawableDemoActivity extends AppCompatActivity {

    @Bind(R.id.transition)
    View transition;
    @Bind(R.id.scale)
    View scale;
    @Bind(R.id.bitmap)
    TextView bitmap;
    @Bind(R.id.shape)
    TextView shape;
    @Bind(R.id.layer)
    TextView layer;
    @Bind(R.id.state)
    TextView state;
    @Bind(R.id.levelList)
    TextView levelList;
    @Bind(R.id.inset)
    TextView inset;
    @Bind(R.id.clip)
    TextView clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_demo);
        ButterKnife.bind(this);

        TransitionDrawable transitionDrawable = (TransitionDrawable) transition.getBackground();
        transitionDrawable.startTransition(1000);
        transitionDrawable.startTransition(2000);

        ScaleDrawable scaleDrawable = (ScaleDrawable) scale.getBackground();
        scaleDrawable.setLevel(500);

        LevelListDrawable levelListDrawable = (LevelListDrawable) levelList.getBackground();
        levelListDrawable.setLevel(0);

        ClipDrawable clipDrawable = (ClipDrawable) clip.getBackground();
        clipDrawable.setLevel(1000);
    }


}
