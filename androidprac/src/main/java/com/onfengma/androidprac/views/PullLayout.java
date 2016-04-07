package com.onfengma.androidprac.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.onfengma.androidprac.utils.ViewUtils;

/**
 * @author yfchu
 * @date 2016/4/5
 */
public class PullLayout extends LinearLayout {

    // 松手刷新闸值（dip）
    private final static int LOOSE_VALUE = 80;
    // 最小滑动距离
    private static int mTouchSlop;

    private View contentView;
    private PullHeaderView headerView;

    private Scroller scroller;

    private float mLastY = -1;

    public interface PullListener{
        void onReadyToLoose();
    }

    public enum PullStatus {
        PULLING,
        CAN_LOOSE,
        NORMAL,
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        initScroller();
        addHeaderView();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void addHeaderView() {
        headerView = new PullHeaderView(getContext());
        addView(headerView);
        setContentTop(0);
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
        System.out.println("--------onInterceptTouchEvent--------" + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 滑动距离大于最小距离 && 下滑状态 && 可以被下滑
                float diff = ev.getRawY() - mLastY;
                if (mTouchSlop < diff && ev.getRawY() > mLastY && isReadyToPull()) {
                    mLastY = ev.getRawY();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("--------onTouchEvent--------" + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int topNew = (int) ((event.getRawY() - mLastY));
                topNew = (int) (topNew * 0.5);
                setContentTop(topNew < 0 ? 0 : topNew);
                if (topNew < 0) {
                    contentView.setScrollY(-topNew);
                } else {
                    contentView.setScrollY(0);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                scroller.startScroll(0, getContentTop(), 0, -getContentTop());
                invalidate();
                mLastY = -1;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mLastY = event.getRawY();
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isReadyToPull() {
        return headerView.getHeight() == 0 && contentView.getScrollY() == 0;
    }

    private int getContentTop() {
        return headerView.getHeight();
    }

    private void setContentTop(int top) {

        LayoutParams lp = (LayoutParams) headerView.getLayoutParams();
        if (lp == null) {
            lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, top);
        }
        lp.height = top;
        headerView.setLayoutParams(lp);

        if(top >= ViewUtils.dipToPixels(LOOSE_VALUE, getResources().getDisplayMetrics())) {
            headerView.updatePullStatus(PullStatus.CAN_LOOSE);
        } else if (top == 0) {
            headerView.updatePullStatus(PullStatus.NORMAL);
        } else {
            headerView.updatePullStatus(PullStatus.PULLING);
        }

        headerView.updateHeight(top);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            setContentTop(scroller.getCurrY() < 0 ? 0 : scroller.getCurrY());
            postInvalidate();
        }
    }


}


