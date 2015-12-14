package com.onfengma.material;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZoomHeaderActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener{

    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.text)
    TextView textView;
    @Bind(R.id.scroll)
    NestedScrollView scrollView;

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
                scrollView.scrollTo(scrollView.getScrollX(), 500);
            }
        }, 1000);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        // Toast.makeText(ZoomHeaderActivity.this, "scroll : " + scrollY, Toast.LENGTH_SHORT).show();
    }

    class MyBehavier extends CoordinatorLayout.Behavior<View> {

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
            child.setTranslationY(-target.getScrollY() * 0.65f);
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
            return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }
    }

}
