package com.onfengma.androidprac.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.onfengma.androidprac.R;

/**
 * @author yfchu
 * @date 2016/5/13
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context, R.style.PresentDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_demo_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hide();
        }
        return super.onTouchEvent(event);
    }
}
