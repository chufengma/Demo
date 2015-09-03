package com.onfengma.androidprac;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.onfengma.androidprac.Config.Constant;
import com.onfengma.androidprac.network.VolleyHelper;
import com.onfengma.androidprac.utils.Logger;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

@EActivity(R.layout.activity_vollay_demo)
public class VolleyDemoActivity extends AppCompatActivity {

    @ViewById
    Button jsonBtn;
    @ViewById
    Button imageBtn;
    @ViewById
    Button stringBtn;
    @ViewById
    Button customBtn;
    @ViewById
    TextView textHolder;
    @ViewById
    ImageView imageHolder;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = VolleyHelper.getInstance(this).getRequestQueue();
    }

    @Click
    public void jsonBtn() {
        queue.add(new JsonObjectRequest(Constant.SERVER_URL, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textHolder.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textHolder.setText("Error ! no data come !");
            }
        }));
    }

    @Click
    public void stringBtn() {
        queue.add(new StringRequest(Constant.SERVER_URL, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                textHolder.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error:" + error.getMessage());
                textHolder.setText("Error ! no data come !");
            }
        }));
    }

    @Click
    public void imageBtn() {
        Logger.i("image button start");
        queue.add(new ImageRequest(Constant.SERVER_URL + "images/demo.jpg", new Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap response) {
                imageHolder.setImageBitmap(response);
            }
        }, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing but log out
                Logger.i("error:" + error.getMessage());
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vollay_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
