package com.onfengma.androidprac.utils;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.onfengma.androidprac.MainApplication;

/**
 * Created by yifeng on 15/12/8.
 */
public class ViewUtils {

    public static int dipToPixels(float dip, DisplayMetrics displayMetrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    public static int dipToPixels(float dip, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

}
