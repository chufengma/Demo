package com.onfengma.androidprac;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.onfengma.androidprac.network.VolleyHelper;
import com.onfengma.androidprac.utils.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkHttpClientActivity extends AppCompatActivity {

    @Bind(R.id.text_holder)
    TextView textHolder;
    @Bind(R.id.iamge_holder)
    ImageView imageHolder;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_client);
        ButterKnife.bind(this);
        queue = VolleyHelper.getInstance(this).getRequestQueue();
    }

    @OnClick(R.id.json)
    void onJsonClick() {
    }

    @OnClick(R.id.bitmap)
    void onImageClick() {
        queue.add(new ImageRequest("http://images.china.cn/attachement/jpg/site1000/20150903/6c0b840a258b1751dd6c2e.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageHolder.setImageBitmap(response);
            }
        }, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels, Bitmap.Config.ARGB_4444, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.i("error:" + error.getLocalizedMessage());
            }
        }));
    }

    @OnClick(R.id.string)
    void onStringClick() {
        queue.add(new StringRequest("http://www.dytt8.net/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textHolder.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.i("error:" + error.getLocalizedMessage());
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ok_http_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
