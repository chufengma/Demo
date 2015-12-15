package com.onfengma.material;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by yifeng on 15/12/8.
 */
public class ViewUtils {

    public static int dipToPixels(float dip, DisplayMetrics displayMetrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

}
