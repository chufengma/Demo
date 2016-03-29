package com.onfengma.androidprac.views;

import android.content.Context;
import android.support.v4.animation.AnimatorListenerCompat;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.onfengma.androidprac.R;
import com.onfengma.androidprac.utils.ViewUtils;

/**
 * @author yfchu
 * @date 2016/3/16
 */
public class SwipeShowView extends FrameLayout {

    private View containerView;
    private Scroller scroller;

    private float MIN_SCROLL = 0;
    private float MAX_SCROLL;

    private ViewPropertyAnimatorCompat showAnimator;
    private ViewPropertyAnimatorCompat hideAnimator;

    private boolean showAnimatorStarted;
    private boolean hideAnimatorStarted;

    public SwipeShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        MAX_SCROLL = ViewUtils.dipToPixels(255, getResources().getDisplayMetrics());
    }

    public SwipeShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.swipe_show_dialog, this);
        containerView = findViewById(R.id.container);
        scroller = new Scroller(getContext());
        initAnimator();
        containerView.setOnTouchListener(new OnTouchListener() {

            private float downY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if ((downY - event.getRawY() > 40)) {
                        hide();
                    }
                    break;
                }
                return true;
            }
        });
    }

    private void initAnimator() {


    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void show() {
        if (showAnimatorStarted) {
            return;
        }
        showAnimator = ViewCompat.animate(containerView).translationY(ViewUtils.dipToPixels(0, getResources().getDisplayMetrics())).setDuration(300);
        showAnimator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(View view) {
                setVisibility(View.VISIBLE);
                showAnimatorStarted = true;
                super.onAnimationStart(view);
            }

            @Override
            public void onAnimationEnd(View view) {
                showAnimatorStarted = false;
                super.onAnimationEnd(view);
            }
        });
        showAnimator.start();
    }

    public void hide() {
        if (hideAnimatorStarted) {
            return;
        }
        hideAnimator = ViewCompat.animate(containerView).translationY(ViewUtils.dipToPixels(-250, getResources().getDisplayMetrics())).setDuration(300);
        hideAnimator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(View view) {
                hideAnimatorStarted = false;
                setVisibility(View.GONE);
                super.onAnimationEnd(view);
            }

            @Override
            public void onAnimationStart(View view) {
                hideAnimatorStarted = true;
                super.onAnimationStart(view);
            }
        });
        hideAnimator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hide();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                hide();
                break;
        }
        return true;
    }

    public static class AnimatorListenerAdapter implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {

        }

        @Override
        public void onAnimationEnd(View view) {

        }

        @Override
        public void onAnimationCancel(View view) {

        }
    }
}
