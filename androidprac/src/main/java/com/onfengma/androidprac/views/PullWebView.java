package com.onfengma.androidprac.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.Scroller;

/**
 * @author yfchu
 * @date 2016/4/5
 */
public class PullWebView extends WebView {

    public PullWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller();
    }

    private float mLastY = -1;
    private Scroller scroller;

    private void initScroller() {
        scroller = new Scroller(getContext());
    }

//    private int zeroTop;
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        zeroTop = getTop();
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastY = event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (!canPull(event)) {
//                    mLastY = event.getRawY();
//                    break;
//                }
//                // 可以下拉
//                int top = (int) ((event.getRawY() - mLastY) * 0.55);
//                setTop(top < 0 ? 0 : top);
//                return true;
//            case MotionEvent.ACTION_UP:
//                if (getTop() > 0) {
//                    scroller.startScroll(0, getTop(), 0, -getTop());
//                }
//                mLastY = -1;
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    private boolean canPull(MotionEvent event) {
//        if (event.getRawY() < mLastY && getTop() == 0) {
//            return false;
//        }
//        return getTop() >= 0 && getScrollY() == 0;
//    }
//
//    @Override
//    public void computeScroll() {
//        if (scroller.computeScrollOffset()) {
//            setTop(scroller.getCurrY() < 0 ? 0 : scroller.getCurrY());
//            postInvalidate();
//        }
//        super.computeScroll();
//    }


}
