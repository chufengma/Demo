package com.onfengma.androidprac.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;

/**
 * Created by yifeng on 16/1/20.
 */
public class ScrollDemoLayout extends ViewGroup {

    OverScroller scroller;

    public ScrollDemoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollDemoLayout(Context context) {
        this(context, null, 0);
    }

    public ScrollDemoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new OverScroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(0, currentHeight, childView.getMeasuredWidth(), currentHeight + childView.getMeasuredHeight());
            currentHeight += childView.getMeasuredHeight();
        }
    }

    int lastY;
    int distanceY;
    int initScrollY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getY();
                initScrollY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = (int) (event.getY() - lastY);
                scrollTo(0, initScrollY - distanceY);
                break;
            case MotionEvent.ACTION_UP:
                // scroller.startScroll(0, getScrollY(), 0, -distanceY);
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            invalidate();
        }
    }
}
