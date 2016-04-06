package com.onfengma.androidprac.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.onfengma.androidprac.R;

/**
 * @author yfchu
 * @date 2016/4/5
 */
public class PullLayout extends LinearLayout {

    private final static int MAX_TOP = 500;

    private View contentView;
    private int mTouchSlop;
    private float mLastY = -1;
    private Scroller scroller;

    private View headerView;
    private TextView headTextView;

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        initScroller();
        addHeaderView();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void addHeaderView() {
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.pull_layout_header, this, false);
        headTextView = (TextView) headerView.findViewById(R.id.header_text);
        addView(headerView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(1);
    }

    private void initScroller() {
        scroller = new Scroller(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float diff = ev.getRawY() - mLastY;
                if (mTouchSlop < diff && ev.getRawY() > mLastY && contentTopRight() && contentView.getScrollY() == 0) {
                    mLastY = ev.getRawY();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("---------onTouchEvent--------" + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int topNew = (int) ((event.getRawY() - mLastY));
                topNew = (int) (topNew * 0.35);
                setContentTop(topNew < 0 ? 0 : topNew);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (getContentTop() > 0) {
                    scroller.startScroll(0, getContentTop(), 0, -getContentTop());
                }
                mLastY = -1;
                break;
        }
        return super.onTouchEvent(event);
    }

    private int initTop = -1;

    private boolean contentTopRight() {
        if (initTop == -1) {
            initTop = contentView.getTop();
            LayoutParams lp = (LayoutParams)headerView.getLayoutParams();
            lp.setMargins(0, 0, 0, 0);
            headerView.setLayoutParams(lp);
            headerView.setTop(-initTop);
            return false;
        }
        return getContentTop() == initTop;
    }

    private int getContentTop() {
        return contentView.getTop();
    }

    private void setContentTop(int top) {
        contentView.setTop(top);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            setContentTop(scroller.getCurrY() < 0 ? 0 : scroller.getCurrY());
        }
        postInvalidate();
        super.computeScroll();
    }


}
