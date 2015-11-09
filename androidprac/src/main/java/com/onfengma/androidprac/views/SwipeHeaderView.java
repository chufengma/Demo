package com.onfengma.androidprac.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.onfengma.androidprac.R;

/**
 * Created by chuyifeng on 2015/11/6.
 */
public class SwipeHeaderView extends LinearLayout {

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private ImageView mArrowImageView;
    private ProgressBar mProgressBar;
    private boolean arrowUp;

    public SwipeHeaderView(Context context) {
        super(context);
        init();
    }

    public SwipeHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
        View view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.swipe_header_view, null);
        addView(view, lp);
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView) findViewById(R.id.xlist_view_header_arrow);

        mProgressBar = (ProgressBar) findViewById(R.id.xlist_view_header_progressbar);
        // Rotate clockwise if toDegrees is bigger than fromDegrees
        mRotateUpAnim = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        mRotateUpAnim.setDuration(180);
        mRotateUpAnim.setFillAfter(true);
        mRotateUpAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                arrowUp = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                arrowUp = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(180);
        mRotateDownAnim.setFillAfter(true);
        setPressToDown();
    }

    public void setRefreshing() {
        mArrowImageView.clearAnimation();
        mArrowImageView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void setLooseState() {
        if (!arrowUp) {
            mArrowImageView.clearAnimation();
            mArrowImageView.startAnimation(mRotateUpAnim);
            mArrowImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void setRefreshComplete() {
        mArrowImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    public void setPressToDown() {
        arrowUp = false;
        mArrowImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

}
