package com.onfengma.androidprac.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by chuyifeng on 2015/9/10.
 */
public class DemoButton extends Button {

    public DemoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "button on touched!", Toast.LENGTH_SHORT).show();
        return false;
    }
}
