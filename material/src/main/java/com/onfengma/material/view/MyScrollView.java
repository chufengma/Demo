package com.onfengma.material.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by yifeng on 15/12/15.
 */
public class MyScrollView extends NestedScrollView {

    private float flingVelocity = 1;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFlingVelocity(int flingVelocity) {
        this.flingVelocity = flingVelocity;
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void doScrollY(int delta) {
    }

}
