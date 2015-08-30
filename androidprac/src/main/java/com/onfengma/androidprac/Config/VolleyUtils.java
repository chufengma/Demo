package com.onfengma.androidprac.Config;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by chufengma on 15/8/30.
 */
public class VolleyUtils {

    private static VolleyUtils instance;
    private RequestQueue requestQueue;
    private Context applicationContext;

    private VolleyUtils(Context context) {
        this.applicationContext = context;
        requestQueue = Volley.newRequestQueue(applicationContext);
    }

    public static VolleyUtils getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyUtils(context.getApplicationContext());
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

}
