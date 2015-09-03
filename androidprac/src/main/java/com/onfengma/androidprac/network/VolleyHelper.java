package com.onfengma.androidprac.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

public class VolleyHelper {

    private static VolleyHelper instance;
    private RequestQueue requestQueue;
    private Context applicationContext;
    private OkHttpClient client;

    private VolleyHelper(Context context) {
        this.applicationContext = context;
        client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());
        requestQueue = Volley.newRequestQueue(applicationContext, new OkhttpStack(client));
    }

    public static VolleyHelper getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyHelper(context.getApplicationContext());
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

}
