package com.onfengma.androidprac.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.onfengma.androidprac.R;

/**
 * @author yfchu
 * @date 2016/3/16
 */
public class SwipeShowDialog extends Dialog {

    public SwipeShowDialog(Context context) {
        super(context, R.style.PresentDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_show_dialog);
    }
}
