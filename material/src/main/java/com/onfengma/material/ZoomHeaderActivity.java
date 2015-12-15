package com.onfengma.material;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.onfengma.material.view.MyScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZoomHeaderActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener,View.OnTouchListener {

    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.text)
    TextView textView;
    @Bind(R.id.scroll)
    MyScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_header);
        ButterKnife.bind(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) header.getLayoutParams();
        lp.setBehavior(new MyBehavier());
        header.setLayoutParams(lp);
        scrollView.setOnScrollChangeListener(this);
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // scrollView.scrollTo(scrollView.getScrollX(), ViewUtils.dipToPixels(150, getResources().getDisplayMetrics()));
                // header.setTranslationY(-scrollView.getScrollY() * 0.65f);
            }
        }, 1000);

        scrollView.setOnTouchListener(this);
    }

    float position = -1;
    boolean isScaling = false;
    float initTransY;
    float lastPosition;

    float donwY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                donwY = event.getRawY();
            case MotionEvent.ACTION_MOVE:
                if (event.getRawY() - donwY < 0) {
                    return false;
                }

                if (scrollView.getScrollY() != 0) {
                    return false;
                }

                if (!isScaling && scrollView.getScrollY() == 0) {
                    position = event.getRawY();
                    lastPosition = position;
                    initTransY = scrollView.getTranslationY();
                }
                isScaling = true;

                float distance = (event.getRawY() - lastPosition) * 0.2f;
                scrollView.setTranslationY(scrollView.getTranslationY() + distance);
                float scale = 1.0f + (scrollView.getTranslationY() - initTransY) / (float)getResources().getDisplayMetrics().heightPixels;
                if (scale <= 1.0f) {
                    scale = 1.0f;
                }
                header.setScaleX(scale);
                header.setScaleY(scale);

                lastPosition = event.getRawY();
                return true;
            case MotionEvent.ACTION_UP:
                if (isScaling) {
                    stopScale();
                }
                break;
        }
        return false;
    }

    private void stopScale() {
        position = 0;
        scrollView.setTranslationY(initTransY);
        isScaling = false;
        header.setScaleX(1f);
        header.setScaleY(1f);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        header.setTranslationY(-scrollView.getScrollY() * 0.65f);
    }

    class MyBehavier extends CoordinatorLayout.Behavior<View> {

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
            return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }
    }

}
